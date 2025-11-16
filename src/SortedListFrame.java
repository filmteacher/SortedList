import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;

/*
/ Comp Programming II - Lab10 - Sorted ArrayList
/ @author Matt Bennett
/ GUI refactored from my TagExtractor Lab
*/

public class SortedListFrame extends JFrame
{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel operationsPnl;
    JPanel textPnl;
    JPanel buttonsPnl;

    JLabel title;

    JButton insertBtn;
    JTextField insertFld;
    String insertText;

    JButton searchBtn;
    JTextField searchFld;
    String searchText;

    JTextArea resultsArea;
    JScrollPane resultsScroller;
    String resultsText;

    JButton quitBtn;
    JFrame frameQuit = new JFrame("Confirmation");

    int marginSize = 15;
    int searchIndex;

    public SortedListFrame()
    {
        createMainPnl();

        createTitlePnl();
        mainPnl.add(titlePnl);

        createOperationsPnl();
        mainPnl.add(operationsPnl);

        createTextPnl();
        mainPnl.add(textPnl);

        createButtonsPnl();
        mainPnl.add(buttonsPnl);

        add(mainPnl);

        setTitle("Comp Prog II - Lab10 - Sorted ArrayList");
        setSize(400, 765);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createMainPnl() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 0, marginSize));
        mainPnl.setBackground(Color.LIGHT_GRAY);
    }

    private void createTitlePnl() {
        titlePnl = new JPanel();
        title = new JLabel("Sorted Array List");
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        titlePnl.add(title);
        titlePnl.setBackground(Color.LIGHT_GRAY);
    }

    private void createOperationsPnl() {
        operationsPnl = new JPanel();
        operationsPnl.setLayout(new GridLayout(2, 2));
        operationsPnl.setBackground(Color.LIGHT_GRAY);
        operationsPnl.setPreferredSize(new Dimension(360, 80));

        insertFld = new JTextField();
        insertBtn = new JButton("Insert String");
        insertBtn.addActionListener((ActionEvent ae) ->
        {
            insertText = insertFld.getText();
            SortedList.insertItem(insertText);
            resultsText = insertText + " added.\n\nList:\n" +
                    String.valueOf(SortedList.getList()) + "\n";
            resultsArea.setText(resultsText);
            insertFld.setText("");
        });

        searchFld = new JTextField();
        searchBtn = new JButton("Search for String");
        searchBtn.addActionListener((ActionEvent ae) ->
        {
            searchText = searchFld.getText();
            searchIndex = SortedList.searchList(searchText);
            ArrayList<String> list = SortedList.getList();

            if (searchIndex < list.size() && list.get(searchIndex).equalsIgnoreCase(searchText))
            {
                resultsText = searchText + " found at index " + searchIndex + ".\n\nList:\n" +
                        String.valueOf(SortedList.getList()) + "\n";
            } else {
                resultsText = searchText + " not found. It would be inserted at index " + searchIndex + ".\n\nList:\n" +
                        String.valueOf(SortedList.getList()) + "\n";
            }

            resultsArea.setText(resultsText);
            searchFld.setText("");
        });

        operationsPnl.add(insertFld);
        operationsPnl.add(insertBtn);
        operationsPnl.add(searchFld);
        operationsPnl.add(searchBtn);
    }

    private void createTextPnl() {
        textPnl = new JPanel();
        textPnl.setLayout(new GridLayout(1, 2));
        textPnl.setBackground(Color.WHITE);

        resultsArea = new JTextArea(25, 25);
        resultsArea.setFont(new Font("Verdana", Font.PLAIN, 14));
        resultsArea.setEditable(false);
        resultsArea.setEditable(false);
        resultsArea.setLineWrap(true);
        resultsArea.setWrapStyleWord(true);
        resultsScroller = new JScrollPane(resultsArea);
        resultsScroller.setBorder(new TitledBorder(new EtchedBorder(), "Results:"));
        textPnl.add(resultsScroller);
    }

    private void createButtonsPnl() {
        buttonsPnl = new JPanel();
        buttonsPnl.setLayout(new FlowLayout());
        buttonsPnl.setBackground(Color.LIGHT_GRAY);
        buttonsPnl.setPreferredSize(new Dimension(360, 40));

        quitBtn = new JButton("QUIT the app");
        quitBtn.addActionListener((ActionEvent ae) ->
        {
            int result = JOptionPane.showConfirmDialog(frameQuit, "Are you sure you want to quit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
            else
            {
                frameQuit.dispose();
            }
        });

        buttonsPnl.add(quitBtn);
    }
}