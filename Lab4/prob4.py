from logic import *

A = Symbol("A")
B = Symbol("B")
C = Symbol("C")

knowledge = And(Or(A, Not(B)), Implication(Not(A), C))
query = Implication(Not(B), C)  # ¬B → C

print(model_check(knowledge, query))