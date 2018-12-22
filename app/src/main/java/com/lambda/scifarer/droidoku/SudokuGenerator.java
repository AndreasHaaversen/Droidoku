package com.lambda.scifarer.droidoku;

import java.util.Collections;
import java.util.Random;

public class SudokuGenerator extends SudokuSolver {

    public static final int DEFAULT_PATIENCE = 50;

    public SudokuGenerator(int size) {
        super(size);
    }

    public String generate(int difficulty, int patience) {
        boolean satisfied = false;
        while (!satisfied) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[i][j] = 0;
                }
            }

            generateSudoku();
            satisfied = makeHoles(difficulty, patience);
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                out.append(board[i][j]);
            }
        }
        return out.toString();
    }

    private boolean makeHoles(int difficulty, int patience) {
        Random rand = new Random();
        int removed = 0;
        int lastRemoved = 0;
        int tries = 0;
        while (removed < difficulty) {
            if (lastRemoved == removed) {
                tries++;
            }
            if (tries > patience) {
                return false;
            }

            lastRemoved = removed;
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            if (x != y && board[x][y] != 0 && board[y][x] != 0) {
                if (removeAndTestPair(x, y, y, x)) {
                    removed += 2;
                }
            } else if (board[x][y] != 0) {
                if (removeAndTest(x, y)) {
                    removed += 1;
                }
            } else if (board[y][x] != 0) {
                if (removeAndTest(x, y)) {
                    removed += 1;
                }
            }
        }
        return true;
    }

    private boolean removeAndTestPair(int x1, int y1, int x2, int y2) {
        int tmp1 = board[x1][y1];
        int tmp2 = board[x2][y2];
        board[x1][y1] = 0;
        board[x2][y2] = 0;
        super.solve(board);
        if (super.num_solutions == 1) {
            return true;
        } else {
            board[x1][y1] = tmp1;
            board[x2][y2] = tmp2;
            return false;
        }
    }

    private boolean removeAndTest(int x, int y) {
        int tmp = board[x][y];
        board[x][y] = 0;
        super.solve(board);
        if (super.num_solutions == 1) {
            return true;
        } else {
            board[x][y] = tmp;
            return false;
        }
    }

    private boolean generateSudoku() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == 0) {
                    Collections.shuffle(allowed);
                    for (int number : allowed) {
                        if (isSafe(row, col, number)) {
                            board[row][col] = number;
                            if (generateSudoku()) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
