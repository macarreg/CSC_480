"""
Tic Tac Toe Player
"""
# This is the solution file that I tested

import math
import random
from copy import deepcopy


X = "X"
O = "O"
EMPTY = None


def initial_state():
  """
  Returns starting state of the board.
  """
  return [[EMPTY, EMPTY, EMPTY],
          [EMPTY, EMPTY, EMPTY],
          [EMPTY, EMPTY, EMPTY]]


def player(board):
    """
    Returns player who has the next turn on a board.
    """
    # Scan board for entries and determine next player:

    X_count = 0
    O_count = 0
    EMPTY_count = 0

    for row in board:
      X_count += row.count(X)
      O_count += row.count(O)
      EMPTY_count += row.count(EMPTY)

    # If X has more squares than O, its O's turn:
    if X_count > O_count:
      return O
    # Otherwise it is X's turn:
    else:
      return X


def actions(board):
    """
    Returns set of all possible actions (i, j) available on the board.
    i represents the board row, j the board column, both 0, 1 or 2
    The actions are are represented as the tuple (i, j) where the piece can be placed.
    """
    return [(i, j) for i in range(3) for j in range(3) if board[i][j] == EMPTY]

def result(board, action):
    """
    Returns the board that results from making move (i, j) on the board.
    """

    i = action[0]
    j = action[1]

    # Check move is valid:
    if i not in [0, 1, 2] or j not in [0, 1, 2]:
      raise ValueError(action, board, 'Result function given an invalid board position for action: ')
    elif board[i][j] != EMPTY:
      raise ValueError(action, board, 'Result function tried to perform invalid action on occupaied tile: ')

    # Make a deep copy of the board and update with the current player's move:
    board_copy = deepcopy(board)
    board_copy[i][j] = player(board)

    return board_copy


def winner(board):
    """
    Returns the winner of the game, if there is one.
    otherwise return None
    """
    # Check rows:
    for row in board:
        if row[0] == row[1] == row[2] != EMPTY:
            return row[0]

    # Check columns:
    for j in range(3):
        if board[0][j] == board[1][j] == board[2][j] != EMPTY:
            return board[0][j]

    # Check Diagonals:
    if board[0][0] == board[1][1] == board[2][2] != EMPTY:
        return board[0][0]
    if board[0][2] == board[1][1] == board[2][0] != EMPTY:
        return board[0][2]

    # Otherwise no current winner, return None
    return None


def terminal(board):
    """
    Returns True if game is over, False otherwise.
    """ 
    # Game is over if it is a winning board or all tiles are full (no actions):

    if winner(board) or not actions(board):
      return True
    else:
      return False


def utility(board):
    """
    Returns 1 if X has won the game, -1 if O has won, 0 otherwise.
    """

    if winner(board) == 'X':
      return 1
    elif winner(board) == 'O':
      return -1
    else:
      return 0

actions_explored = 0


def minimax(board):
    """
    Returns the optimal action for the current player on the board.
    'X' Player is trying to maximise the score, 'O' Player is trying to minimise it
    """
    if terminal(board):
        return None  # No action available if terminal

    best_action = None
    if player(board) == X:
        best_value = -math.inf
        for action in actions(board):
            value = minimax_value(result(board, action))
            if value > best_value:
                best_value = value
                best_action = action
    else:  # O's turn
        best_value = math.inf
        for action in actions(board):
            value = minimax_value(result(board, action))
            if value < best_value:
                best_value = value
                best_action = action

    return best_action

def minimax_value(board):
    """
    Returns the minimax value of the board.
    """
    if terminal(board):
        return utility(board)

    if player(board) == X:
        best_value = -math.inf
        for action in actions(board):
            value = minimax_value(result(board, action))
            best_value = max(best_value, value)
        return best_value
    else:  # O's turn
        best_value = math.inf
        for action in actions(board):
            value = minimax_value(result(board, action))
            best_value = min(best_value, value)
        return best_value

