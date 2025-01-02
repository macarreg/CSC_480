from logic import *

P = Symbol("P")
Q = Symbol("Q")
R = Symbol("R")
S = Symbol("S")

knowledge = Implication(Or(P, Q), Or(R, S))
query = And(Implication(P, Q), Implication(R, S))

print(model_check(knowledge, query))