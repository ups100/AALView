package pl.edu.pw.elka.aal;

import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JPanel;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.Plot;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

public class PlotComponent {

	public static JPanel createPlot(Vector<Integer> data, Vector<Integer> values)
	{
		
		
		double midle = 0;
		double midleSize = 0;
		if((data.size() % 2) == 1)
		{
			midle = values.get(data.size()/2 + 1);
			midleSize = data.get(data.size()/2 + 1);
		}
		else {
			midle = (values.get(data.size()/2) + values.get(data.size()/2 + 1))/2.0;
			midleSize = (data.get(data.size()/2) + data.get(data.size()/2 + 1))/2.0; 
		}
		
		@SuppressWarnings("unchecked")
		DataTable dataTable = new DataTable(Integer.class, Double.class);
		
		double medianValueTeoretic = midleSize*midleSize*Math.log(midleSize)*Math.log(midleSize);//*Math.log(midleSize);
		for(int i = 0; i < data.size(); ++i) {
			double valueTeoretic = data.get(i)*data.get(i)*Math.log(data.get(i))*Math.log(data.get(i));//*Math.log(data.get(i));
			
			dataTable.add( data.get(i), (values.get(i)*medianValueTeoretic)/(valueTeoretic*midle));
			System.out.println((values.get(i)*medianValueTeoretic)/(valueTeoretic*midle));
		}
		
		
		System.out.println("Srodek: "+midleSize +"Jego wartosc: " + midle);
		@SuppressWarnings("unchecked")
		DataTable dataTable2 = new DataTable(Integer.class, Double.class);
		for(int i = 0; i < data.size(); ++i) {
			dataTable2.add(data.get(i), 1.0);
		}
		
		
		Collections.sort(values);
		
		DataSource dataSerie = new DataSeries("Asymptotic/mediane", dataTable);
		DataSource dataSerie2 = new DataSeries("1", dataTable2);
		
		Color color = new Color(0.0f, 0.3f, 1.0f);
		Color color2 = new Color(1.0f, 0.3f, 0.0f);
		
		XYPlot plot = new XYPlot(dataSerie, dataSerie2);
		
		LineRenderer lines = new DefaultLineRenderer2D();
		lines.setSetting(LineRenderer.COLOR, color);
		plot.setLineRenderer(dataSerie, lines);
		
		LineRenderer lines2 = new DefaultLineRenderer2D();
		lines2.setSetting(LineRenderer.COLOR, color2);
		plot.setLineRenderer(dataSerie2, lines2);
		
		//plot.getPointRenderer(dataSerie).setSetting(PointRenderer.COLOR, color);
		plot.setPointRenderer(dataSerie, null);
		plot.setPointRenderer(dataSerie2, null);
		
		plot.setSetting(XYPlot.TITLE, "Asymptotic/mediane");
		
		plot.setSetting(XYPlot.TITLE_FONT, new Font(Font.SANS_SERIF, Font.BOLD, 24));
		
		//AxisRenderer rendererX = new LogarithmicRenderer2D();
		//plot.setAxisRenderer(XYPlot.AXIS_X, rendererX);
		plot.getAxisRenderer(XYPlot.AXIS_X).setSettingDefault(AxisRenderer.LABEL, "Number of cities");
		
		//AxisRenderer rendererY = new LogarithmicRenderer2D();
		//plot.setAxisRenderer(XYPlot.AXIS_Y, rendererY);
		plot.getAxisRenderer(XYPlot.AXIS_Y).setSettingDefault(AxisRenderer.LABEL, "Value");
		//plot.getAxisRenderer(XYPlot.AXIS_Y).setSetting(AxisRenderer.TICK_LABELS_FORMAT, new DecimalFormat("0 ms"));
		plot.getAxisRenderer(XYPlot.AXIS_Y).setSetting(AxisRenderer.INTERSECTION, data.firstElement());
		plot.getAxis(XYPlot.AXIS_Y).setRange(-0.15, 1.5);
		plot.getAxisRenderer(XYPlot.AXIS_Y).setSetting(AxisRenderer.TICKS, true);
		plot.getAxisRenderer(XYPlot.AXIS_Y).setSetting(AxisRenderer.TICK_LABELS, true);
		
		plot.getAxisRenderer(XYPlot.AXIS_X).setSettingDefault(AxisRenderer.LABEL, "Number of cities");
		plot.getAxis(XYPlot.AXIS_X).setRange(data.firstElement() - 100, data.lastElement() +100);
		plot.getAxisRenderer(XYPlot.AXIS_X).setSetting(AxisRenderer.TICK_LABELS, true);
		plot.getAxisRenderer(XYPlot.AXIS_X).setSetting(AxisRenderer.TICKS, true);
		plot.getAxisRenderer(XYPlot.AXIS_X).setSetting(AxisRenderer.INTERSECTION, 0);
		
		plot.setSetting(Plot.LEGEND, true);
		InteractivePanel panel = new InteractivePanel(plot);
		
		return panel;
	}
}
