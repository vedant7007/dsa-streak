#!/usr/bin/env python3
"""Build the daily DSA notes PDF.

Usage: python3 scripts/build_pdf.py YYYY-MM-DD
Reads  days/<date>/notes.json and each problem's Solution.java
Writes pdfs/<date>.pdf

notes.json schema:
{
  "date": "2026-11-10",
  "problems": [
    {
      "folder": "01-climbing-stairs-ii",
      "id": 3693, "title": "...", "slug": "...", "difficulty": "Medium",
      "link": "https://leetcode.com/problems/<slug>/",
      "tags": ["Dynamic Programming"],
      "potd": true,
      "problem": "Short restatement of the problem in plain words.",
      "examples": [{"input": "...", "output": "...", "explanation": "..."}],
      "intuition": "Why this approach works, in simple words.",
      "approach": ["Step 1 ...", "Step 2 ..."],
      "dryRun": ["line 1 of the walkthrough on Example 1", "line 2 ..."],
      "complexity": {"time": "O(n)", "space": "O(1)"},
      "edgeCases": ["empty input ...", "..."],
      "takeaway": "Pattern to remember for similar problems."
    }
  ]
}
"""
import json
import sys
from pathlib import Path
from xml.sax.saxutils import escape

from reportlab.lib import colors
from reportlab.lib.enums import TA_CENTER
from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import ParagraphStyle, getSampleStyleSheet
from reportlab.lib.units import mm
from reportlab.platypus import (KeepTogether, PageBreak, Paragraph, Preformatted,
                                SimpleDocTemplate, Spacer, Table, TableStyle)

ROOT = Path(__file__).resolve().parent.parent
DIFF_COLORS = {"Easy": "#00a86b", "Medium": "#e69500", "Hard": "#d64545"}

ss = getSampleStyleSheet()
H_TITLE = ParagraphStyle("t", parent=ss["Title"], fontSize=22, spaceAfter=4)
SUB = ParagraphStyle("s", parent=ss["Normal"], alignment=TA_CENTER, textColor=colors.HexColor("#555555"))
H1 = ParagraphStyle("h1", parent=ss["Heading1"], fontSize=16, spaceBefore=0, spaceAfter=4)
H2 = ParagraphStyle("h2", parent=ss["Heading3"], fontSize=11.5, spaceBefore=8, spaceAfter=3,
                    textColor=colors.HexColor("#1f3b73"))
BODY = ParagraphStyle("b", parent=ss["Normal"], fontSize=10, leading=14)
BUL = ParagraphStyle("bul", parent=BODY, leftIndent=12, bulletIndent=2)
CODE = ParagraphStyle("c", fontName="Courier", fontSize=8.3, leading=10.4,
                      backColor=colors.HexColor("#f4f6f8"), borderPadding=6,
                      borderColor=colors.HexColor("#d9dee3"), borderWidth=0.5)
MONO = ParagraphStyle("m", parent=BODY, fontName="Courier", fontSize=9, leading=12)


def p(text, style=BODY):
    return Paragraph(escape(str(text)), style)


def bullets(items, numbered=False):
    out = []
    for i, it in enumerate(items or [], 1):
        out.append(Paragraph(escape(str(it)), BUL, bulletText=f"{i}." if numbered else "•"))
    return out


def wrap_code(code, width=95):
    lines = []
    for line in code.expandtabs(4).splitlines():
        while len(line) > width:
            lines.append(line[:width])
            line = "        " + line[width:]
        lines.append(line)
    return "\n".join(lines)


def problem_block(day_dir, n, prob):
    diff = prob.get("difficulty", "")
    color = DIFF_COLORS.get(diff, "#333333")
    potd = "  |  Problem of the Day" if prob.get("potd") else ""
    els = [Paragraph(f"{n}. {escape(prob['title'])}", H1),
           Paragraph(f'<font color="{color}"><b>{escape(diff)}</b></font>'
                     f'  |  #{escape(str(prob.get("id", "")))}'
                     f'  |  {escape(", ".join(prob.get("tags", [])))}{potd}', BODY),
           Paragraph(f'<link href="{escape(prob.get("link", ""))}" color="blue">'
                     f'{escape(prob.get("link", ""))}</link>', BODY)]

    els += [Paragraph("Problem", H2), p(prob.get("problem", ""))]

    if prob.get("examples"):
        els.append(Paragraph("Examples", H2))
        for i, ex in enumerate(prob["examples"], 1):
            rows = [[p(f"Example {i}", BODY), ""],
                    [p("Input"), p(ex.get("input", ""), MONO)],
                    [p("Output"), p(ex.get("output", ""), MONO)]]
            if ex.get("explanation"):
                rows.append([p("Why"), p(ex["explanation"])])
            t = Table(rows, colWidths=[22 * mm, 148 * mm])
            t.setStyle(TableStyle([
                ("SPAN", (0, 0), (1, 0)),
                ("BACKGROUND", (0, 0), (1, 0), colors.HexColor("#e8eef7")),
                ("BOX", (0, 0), (-1, -1), 0.5, colors.HexColor("#c5cdd8")),
                ("VALIGN", (0, 0), (-1, -1), "TOP"),
            ]))
            els += [t, Spacer(1, 4)]

    els += [Paragraph("Intuition", H2), p(prob.get("intuition", ""))]
    els += [Paragraph("Approach", H2)] + bullets(prob.get("approach"), numbered=True)
    if prob.get("dryRun"):
        els += [Paragraph("Dry run on Example 1", H2)] + bullets(prob["dryRun"])

    sol = day_dir / prob["folder"] / "Solution.java"
    code = sol.read_text() if sol.exists() else "// Solution.java missing"
    els += [Paragraph("Java solution", H2), Preformatted(wrap_code(code), CODE)]

    cx = prob.get("complexity", {})
    els += [Paragraph("Complexity", H2),
            p(f"Time: {cx.get('time', '?')}    Space: {cx.get('space', '?')}")]
    if prob.get("edgeCases"):
        els += [Paragraph("Edge cases handled", H2)] + bullets(prob["edgeCases"])
    if prob.get("takeaway"):
        els += [Paragraph("Pattern to remember", H2), p(prob["takeaway"])]
    return els


def footer(canvas, doc):
    canvas.saveState()
    canvas.setFont("Helvetica", 8)
    canvas.setFillColor(colors.HexColor("#888888"))
    canvas.drawRightString(A4[0] - 15 * mm, 10 * mm, f"Page {doc.page}")
    canvas.drawString(15 * mm, 10 * mm, f"DSA Streak  |  {doc.title}")
    canvas.restoreState()


def main():
    date = sys.argv[1]
    day_dir = ROOT / "days" / date
    notes = json.loads((day_dir / "notes.json").read_text())
    out = ROOT / "pdfs" / f"{date}.pdf"
    out.parent.mkdir(exist_ok=True)

    probs = notes["problems"]
    story = [Paragraph("DSA Streak: Daily LeetCode in Java", H_TITLE),
             Paragraph(f"{escape(date)}  |  {len(probs)} problems", SUB), Spacer(1, 10)]
    rows = [["#", "Problem", "Difficulty", "Topic"]]
    for i, pr in enumerate(probs, 1):
        rows.append([str(i), Paragraph(escape(pr["title"]), BODY), pr.get("difficulty", ""),
                     Paragraph(escape(", ".join(pr.get("tags", [])[:2])), BODY)])
    t = Table(rows, colWidths=[10 * mm, 85 * mm, 25 * mm, 50 * mm])
    t.setStyle(TableStyle([
        ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#1f3b73")),
        ("TEXTCOLOR", (0, 0), (-1, 0), colors.white),
        ("GRID", (0, 0), (-1, -1), 0.4, colors.HexColor("#c5cdd8")),
        ("VALIGN", (0, 0), (-1, -1), "MIDDLE"),
    ]))
    story += [t]
    for i, pr in enumerate(probs, 1):
        story += [PageBreak()] + problem_block(day_dir, i, pr)

    doc = SimpleDocTemplate(str(out), pagesize=A4, title=date,
                            leftMargin=15 * mm, rightMargin=15 * mm,
                            topMargin=15 * mm, bottomMargin=18 * mm)
    doc.build(story, onFirstPage=footer, onLaterPages=footer)
    print(out)


if __name__ == "__main__":
    main()
