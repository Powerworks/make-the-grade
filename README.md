# Make the Grade — reference system

The running case study for the **Agentic Architecture Masterclass** replica.
Original kata: [nealford.com/katas/kata?id=MakeTheGrade](http://nealford.com/katas/kata?id=MakeTheGrade)
(Neal Ford). This rebuild is a plain-Java re-implementation for exercising
deterministic guardrails — see [`agent-architecture-masterclass`](..).

## The kata brief

> A very large and populous state would like a new system to support
> standardized testing across all public school systems, grades 3–12.
> Users: 40,000+ students, 2,000 graders, 50 administrators. Students can
> only test at testing centers around the state (mostly on school premises,
> not all). Tests are multiple choice, short answer, and essay. Students take
> a test; results are eventually consolidated into a single location
> representing all tests, with reporting by school, teacher, and student.

## Modules — the "incorrect architecture" scaffolding

Four Maven modules, each a bounded context in embryo:

```
students          — GradeLevel + Student (identity, mandate 3-12)
testing-centers   — Session + EnrollmentService (place/time-bound testing)
grading           — Submission + Score (machine vs human scoring)
reporting         → depends on grading (consolidation, read-only)
```

Deliberate fault lines for the guardrail exercises:

- `reporting → grading` is a **cross-quantum edge** (see
  `coupling-fitness-functions/` to police it).
- Value types leak (`studentId` as String) — tolerated now, discussed in the
  copy-paste/quality exercise.
- No persistence yet: the data-integrity exercise (`make-the-grade-data-integrity/`)
  owns the schema split.

## Build & test

```bash
mvn verify            # compiles all modules, runs unit tests
mvn install           # required once so the guardrails repo can resolve these jars
```

## Where this system gets governed

| Guardrail repo | What it does to *this* repo |
|---|---|
| `make-the-grade-guardrails` | ArchUnit rules fail the build on wrong arrows |
| `coupling-fitness-functions` | Dependency matrix vs quantum boundaries |
| `copy-paste-policy` | Duplication thresholds across modules |
| `make-the-grade-data-integrity` | Which tables belong to which context |
