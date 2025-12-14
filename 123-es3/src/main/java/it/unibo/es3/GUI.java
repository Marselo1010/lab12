package it.unibo.es3;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final List<JButton> cells = new ArrayList<>();
    private final Logics logic;
    private final int width;

    /**
     * Constructor.
     *
     * @param width the size of the grid
     */
    public GUI(final int width) {
        this.width = width;
        this.logic = new LogicsImpl(width);
        logic.randomAsteriskSetUp(3);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Pannello per la griglia
        final JPanel gridPanel = new JPanel(new GridLayout(width, width));
        this.getContentPane().add(gridPanel, BorderLayout.CENTER);

        // Crea i bottoni per la griglia
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final JButton button = new JButton(" ");
                this.cells.add(button);
                gridPanel.add(button);
                button.setFocusable(false); // I bottoni non prendono il focus
            }
        }

        // Pulsante ">" per avanzare
        final JButton nextButton = new JButton(">");
        nextButton.addActionListener(e -> advanceStep());

        // Pannello di controllo con il pulsante
        final JPanel controlPanel = new JPanel();
        controlPanel.add(nextButton);
        this.getContentPane().add(controlPanel, BorderLayout.SOUTH);

        // Mostra lo stato iniziale
        updateAllButtons();

        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Avanza di uno step nell'animazione.
     */
    private void advanceStep() {
        // Esegue un passo nella logica
        logic.updateMap();

        // Aggiorna la visualizzazione
        updateAllButtons();

        // Controlla se tutte le celle sono attive
        if (logic.quit()) {
            dispose();
        }
    }

    /**
     * Aggiorna tutti i bottoni in base allo stato della logica.
     */
    private void updateAllButtons() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final int index = i * width + j;
                final String state = logic.getCellState(j, i);
                cells.get(index).setText(state != null ? state : " ");
            }
        }
    }
}
