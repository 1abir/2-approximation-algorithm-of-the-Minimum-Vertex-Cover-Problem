# 2-approximation-algorithm-of-the-Minimum-Vertex-Cover-Problem


The tasks are described as follows:

1. Implement the 2-approximation algorithm (as described in the textbook by Cormen et al.) of the Minimum Vertex Cover problem.
2. In the approximation algorithm, you need to repeatedly choose an edge until you find a vertex cover. In this assignment you will implement two schemes of choosing edge in each step as follows.
  1. Pick edge randomly.
  2. Pick the edge (_u_,_v_) for which the sum of the uncovered edges of the graph incident to _u_ and _v_ is maximum. You can also think this as picking the edge (_u_,_v_) for which _deg_(_u_) + _deg_(_v_) is maximum where _deg_(_u_) is the degree of _u_ in the current graph (Note that in each step you are removing some edges of the original graph).
3. Experiment using the first four dataset from [here](https://turing.cs.hbg.psu.edu/txn131/vertex_cover.html). Since the edge choice in 2(a) is random, you may get different size of the vertex cover each time you run the algorithm on the same input. Hence run the algorithm with edge choice described in 2(a) five times (for each dataset) and report the best, worst and average result. For 2(b), report the size of the vertex cover. Also report the size of the minimum vertex cover for each dataset (you can find them in the same webpage). You will need to submit a report containing the results (in excel/doc format) along with your source code.
4. Construct a graph for which the 2-approximation algorithm will perform worst (no matter how you pick the edges!). Submit the test case of this graph in a txt file along with your source code and report.