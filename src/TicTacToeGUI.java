import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';
    private boolean gameEnded = false;
    private JButton restartButton;
    private boolean playWithComputer = false;

    public TicTacToeGUI() {
        setTitle("🎉 Tic-Tac-Toe 🎉");
        setSize(420, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        int mode = JOptionPane.showOptionDialog(
                this,
                "Choose Game Mode",
                "Tic-Tac-Toe",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[]{"Play with Friend", "Play with Computer"},
                "Play with Friend"
        );
        if (mode == 1) playWithComputer = true;


        JPanel boardPanel = new JPanel(new GridLayout(3, 3));
        boardPanel.setBackground(new Color(255, 220, 220)); // light pink

        Font font = new Font("Comic Sans MS", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton b = new JButton("");
                b.setFont(font);
                b.setBackground(new Color(255, 255, 180));
                b.setForeground(new Color(80, 80, 80));
                b.setFocusPainted(false);
                b.addActionListener(this);
                buttons[i][j] = b;
                boardPanel.add(b);
            }
        }


        restartButton = new JButton("🔄 Restart");
        restartButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        restartButton.setBackground(new Color(180, 235, 255));
        restartButton.setForeground(Color.BLACK);
        restartButton.setFocusPainted(false);
        restartButton.addActionListener(e -> resetGame());

        add(boardPanel, BorderLayout.CENTER);
        add(restartButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameEnded) return;

        JButton clicked = (JButton) e.getSource();

        if (!clicked.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid move!");
            return;
        }

        clicked.setText(String.valueOf(currentPlayer));
        clicked.setForeground(currentPlayer == 'X' ? new Color(255, 100, 100) : new Color(100, 100, 255));

        if (checkWin(currentPlayer)) {
            JOptionPane.showMessageDialog(this, (playWithComputer && currentPlayer == 'O') ? "Computer wins!" : "Player " + currentPlayer + " wins!");
            gameEnded = true;
            return;
        } else if (isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            gameEnded = true;
            return;
        }

        if (playWithComputer) {
            currentPlayer = 'O';
            makeComputerMove();
            if (checkWin(currentPlayer)) {
                JOptionPane.showMessageDialog(this, "Computer wins!");
                gameEnded = true;
                return;
            } else if (isDraw()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                gameEnded = true;
                return;
            }
            currentPlayer = 'X';
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private void makeComputerMove() {
        List<JButton> empty = new ArrayList<>();
        for (JButton[] row : buttons) {
            for (JButton b : row) {
                if (b.getText().equals("")) empty.add(b);
            }
        }

        if (!empty.isEmpty()) {
            JButton move = empty.get(new Random().nextInt(empty.size()));
            move.setText("O");
            move.setForeground(new Color(100, 100, 255)); // Blue for O
        }
    }

    private boolean checkWin(char player) {
        String symbol = String.valueOf(player);

        for (int i = 0; i < 3; i++) {
            if (
                    buttons[i][0].getText().equals(symbol) &&
                            buttons[i][1].getText().equals(symbol) &&
                            buttons[i][2].getText().equals(symbol)
            ) return true;

            if (
                    buttons[0][i].getText().equals(symbol) &&
                            buttons[1][i].getText().equals(symbol) &&
                            buttons[2][i].getText().equals(symbol)
            ) return true;
        }

        if (
                buttons[0][0].getText().equals(symbol) &&
                        buttons[1][1].getText().equals(symbol) &&
                        buttons[2][2].getText().equals(symbol)
        ) return true;

        if (
                buttons[0][2].getText().equals(symbol) &&
                        buttons[1][1].getText().equals(symbol) &&
                        buttons[2][0].getText().equals(symbol)
        ) return true;

        return false;
    }

    private boolean isDraw() {
        for (JButton[] row : buttons) {
            for (JButton b : row) {
                if (b.getText().equals("")) return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (JButton[] row : buttons) {
            for (JButton b : row) {
                b.setText("");
                b.setForeground(Color.DARK_GRAY);
            }
        }
        currentPlayer = 'X';
        gameEnded = false;
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
