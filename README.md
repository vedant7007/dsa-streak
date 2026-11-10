# DSA Streak

Daily LeetCode practice in Java: 5 problems a day (Problem of the Day + 1 Easy + 2 Medium + 1 Hard).

## Layout
```
days/YYYY-MM-DD/
  NN-slug/Solution.java      # exact LeetCode submission
  NN-slug/SolutionTest.java  # runs the examples + edge cases
  notes.json                 # problem, intuition, approach, dry run, complexity
  status.json                # LeetCode submission verdicts
pdfs/YYYY-MM-DD.pdf          # daily notes PDF
solved.json                  # every problem solved so far (no repeats)
```

## Run locally
```
bash scripts/run_tests.sh 2026-11-10
python3 scripts/build_pdf.py 2026-11-10
```
