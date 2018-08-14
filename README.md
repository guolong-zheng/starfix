# Project Name #
StarFix: Automatic Data Structure Repair using Separation Logic

# Mentors #
- ThanhVu Nguyen
- Quang Loc Le
- Quoc-Sang Phan

# Project Summary #
This project is supported by Google Summer of Code 2018. StarFix takes as input a specification written in a separation logic formula and a concrete data structure that fails that specification, and performs on-the-fly repair to make the data conforms with the specification.

StarFix is built on STARLIB, the main component for SL functions in [Java StarFinder](https://github.com/star-finder/jpf-star) (a [GSoC 2017 project](https://summerofcode.withgoogle.com/archive/2017/projects/4789313736802304/)). Given a running program and a separation logic formula *F* describing the desired memory shape, StarFix collects the program heap state *S* at desired location using JAVA REFLECTION. Then it uses a simple model checker to check and locate any mistakes in *S* with repect to *F*. If there is any mistake, StarFix modifies *S* such that *S* => *F*.   

Currently, we have tested StarFix with tree and doubly linked list. The examples are located at src/repair/examples/Tree.java and src/repair/examples/Node.java. StarFix could also automatically generate corrupted data structures given data structure definition in separation logic and number of memory cells.

For more details, please refer to the paper in the following link:
https://github.com/guolong-zheng/StarFix/blob/master/paper.pdf

# Future Work #
- Extending an SL satisfiability solver to support a more expressive fragment with general inductive definitions and arithmetic
- Evaluating ranking techniques to prioritize fixes generated by StarFix
- Exploring optimizations during search
- Using dynamic inference to automatically generate required separation logic specifications from good program states
