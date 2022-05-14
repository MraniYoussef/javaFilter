import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
public class RegexTable {

	public static void main(String[] args) {

		Runnable runner = new Runnable() {

			@Override
			public void run() {

				JFrame frame = new JFrame(" Table");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(600,400);
				
				Object rows[][] = {
						{"M","Mrani",55.25},
						{"S","Sam",56.25},
						{"R","Rony",40.25},
						{"Y","York",30.25},
				};
				String columns[] = {"Symbol","Name","Price"};
				TableModel model = new DefaultTableModel(rows,columns) {
					public Class getColumnClass(int column) {
						Class returnValue;
						if(column >= 0 && column < getColumnCount()) {
							returnValue = getValueAt(0,column).getClass();
						}else {
							returnValue = Object.class;
						}
						return returnValue;
					}
				};
				final JTable table = new JTable(model);
				final TableRowSorter<TableModel> sorter =
						new TableRowSorter<TableModel>(model);
				table.setRowSorter(sorter);
				
				JScrollPane pane = new JScrollPane(table);
				frame.add(pane,BorderLayout.CENTER);
				JPanel panel = new JPanel(new BorderLayout());
				JLabel label = new JLabel("Filter");
				panel.add(label,BorderLayout.WEST);
				final JTextField filterText = new JTextField();
				panel.add(filterText,BorderLayout.CENTER);
				frame.add(pane, BorderLayout.NORTH);
				
				JButton button = new JButton("Filter");
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String text = filterText.getText();
						if(text.length()==0) {
							sorter.setRowFilter(null);
						}else {
							sorter.setRowFilter(RowFilter.regexFilter(text));						
						}
						}
				});
			frame.add(button, BorderLayout.SOUTH);	
			frame.add(filterText);
			frame.setVisible(true);

			
		}

	};
	EventQueue.invokeLater(runner);
	}
}
