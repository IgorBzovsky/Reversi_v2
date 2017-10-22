package Views.Components;

import Views.VisualSettings;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.Serializable;

public class StatisticsTable extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    public StatisticsTable() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(VisualSettings.getTableWidth(), 0));
        setBorder(new EmptyBorder(20, 0, 20, 40));

        table = new JTable();
        table.setBackground(VisualSettings.getTableBackground());
        Object[] columns = {"Black", "White"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columns);
        table.setRowHeight(20);
        table.setShowGrid(false);
        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setCellRenderer(new StatisticsRenderer());
        table.getColumnModel().getColumn(1).setCellRenderer(new StatisticsRenderer());
        scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addRecord(int blackDisksCount, int whiteDisksCount) {
        Object[] rows = new Object[2];
        rows[0] = Integer.toString(blackDisksCount);
        rows[1] = Integer.toString(whiteDisksCount);
        tableModel.addRow(rows);
        revalidate();
        table.scrollRectToVisible(table.getCellRect(table.getRowCount()-1, 0, true));
    }

    public void clear() {
        while(tableModel.getRowCount() > 0)
        {
            tableModel.removeRow(0);
        }
    }
}
