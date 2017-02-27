package se.osbe.emoreactor.brain.demo;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;
import se.osbe.emoreactor.brain.Brain;
import se.osbe.emoreactor.brain.emotions.EmotionBuilder;
import se.osbe.emoreactor.brain.emotions.feelings.FeelingType;
import se.osbe.emoreactor.brain.perception.Perception;
import se.osbe.emoreactor.brain.perception.SightPerception;
import se.osbe.emoreactor.brain.reactor.ReactorException;
import se.osbe.emoreactor.helper.BrainHelper;
import se.osbe.emoreactor.helper.DiceHelper;

public class AnimatedLineChartDemo extends Application {
	// Chart
	private LineChart<Number, Number> _chart;
	private NumberAxis _xAxis;
	private Timeline _animation;
	private double _sequence = 0;
	private double _y = 0; // Start value for y-axis
	private final int MAX_DATA_POINTS = 60, MAX = 100, MIN = 0;

	private Map<FeelingType, XYChart.Series<Number, Number>> _dataSeriesMap;

	// Brain
	private Brain _brain; // null = default configuration
	private DiceHelper _dice;
	private BrainHelper _brainhelper;
	private EmotionBuilder _eb;
	private Map<FeelingType, Double> _emoNow;

	public AnimatedLineChartDemo() throws ReactorException {
		// create timeline to add new data every 60th of second
		_animation = new Timeline();
		_animation.getKeyFrames().add(new KeyFrame(Duration.millis(1000), (ActionEvent actionEvent) -> {
			try {
				plotTime();
			} catch (NumberFormatException | ReactorException e) {
				e.printStackTrace();
			}
		}));
		_animation.setCycleCount(Animation.INDEFINITE);

		_dataSeriesMap = new HashMap<>();

		// Init brain
		_brain = new Brain(null);
		_brain.setPerceptionAwarenessPercentage(30);
		_dice = _brain.getBrainConfig().getDiceHelper();
		_brainhelper = _brain.getBrainConfig().getBrainHelper();
		_eb = _brain.getBrainConfig().getEmotionBuilder();
	}

	public Parent createContent() {
		// Setup axis
		final NumberAxis yAxis = new NumberAxis(MIN, MAX, 5);
		yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, null, null));
		yAxis.setLabel("Intesity");

		_xAxis = new NumberAxis(0, MAX_DATA_POINTS, 1);
		_xAxis.setForceZeroInRange(false);
		_xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, null, "s"));
		_xAxis.setLabel("Time");

		// setup chart (put both axis in chart)
		_chart = new LineChart<>(_xAxis, yAxis);
		_chart.setAnimated(true);
		_chart.setLegendVisible(true);
		_chart.setTitle("Brain Emotion Reactor");

		// add starting data
		for (FeelingType feeling : FeelingType.values()) {
			XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>(); // TODO:
																				// Multiply
			dataSeries.getData().add(new XYChart.Data<Number, Number>(_sequence, _y));
			dataSeries.setName(feeling.description());
			_dataSeriesMap.put(feeling, dataSeries);
		}

		// Put prepared data series into chart
		_chart.getData().addAll(_dataSeriesMap.values());

		return _chart;
	}

	private void plotTime() throws NumberFormatException, ReactorException {

		getNextValue();
		_sequence++;
		for (FeelingType feeling : FeelingType.values()) {
			_dataSeriesMap.get(feeling).getData()
					.add(new XYChart.Data<Number, Number>(_sequence, _emoNow.get(feeling)));
		}

		// When max x is reached, start deleting oldest data every tic
		if (_sequence > MAX_DATA_POINTS) {
			_dataSeriesMap.values().forEach(series -> {
				series.getData().remove(0);
			});
		}

		// every hour after 24 move range 1 hour
		if (_sequence > MAX_DATA_POINTS - 1) {
			_xAxis.setLowerBound(_xAxis.getLowerBound() + 1);
			_xAxis.setUpperBound(_xAxis.getUpperBound() + 1);
		}
	}

	private void getNextValue() throws NumberFormatException, ReactorException {
		// if(_brain.isReactorDry()){
		int intensity = _dice.getRandomDoubleBetween(3d, 15d).intValue();
		int duration = _dice.getRandomDoubleBetween(3d, 60d).intValue();
		Perception perception = new SightPerception(
				_eb.addFeelings("*=" + intensity + "," + duration + "s;").build(null));
		_brain.addInboundPerception(perception);
		// }
		_emoNow = _brain.tic();
	}

	public void play() {
		_animation.play();
	}

	@Override
	public void stop() {
		_animation.pause();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.setTitle("Animated Line Chart");
		primaryStage.show();
		play();
	}

	/**
	 * Java main for when running without JavaFX launcher
	 */
	public static void main(String[] args) {
		launch(args);
	}
}