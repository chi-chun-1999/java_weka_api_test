package test1.alogrithm;
import weka.classifiers.trees.*;
import weka.classifiers.*;
import weka.core.Instances;
import weka.classifiers.Evaluation;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;



public class J48Classifier{
	private Instances _datase_instance;
	private Evaluation _evaluation;
	private J48 j48_classifier;
	public J48Classifier()
	{
		j48_classifier=new J48();

	}
	public void getFile(String file_name)
	{
		try
		{
		InputStream io_stream=this.getClass().getClassLoader().getResourceAsStream(file_name);
		if(io_stream==null)
		{
		System.out.println("ddddddddddddddddd");

		}
		//System.out.println(file_name);
		InputStreamReader input_stream_reader=new InputStreamReader(io_stream);

		BufferedReader buffer_reader=new BufferedReader(input_stream_reader);
		_datase_instance=new Instances(buffer_reader);
		System.out.println(_datase_instance.numAttributes());

		}
		catch (Exception e)
		{
			System.out.println("Error Occurred!!!! \n"
                               + e.getMessage());			

		}
	}
	public void evaluationOutcome()
	{
		try{
			_datase_instance.setClassIndex(_datase_instance.numAttributes()-1);
		_evaluation=new Evaluation(_datase_instance);
		_evaluation.crossValidateModel(j48_classifier, _datase_instance, 10,new Random(1));

		}
		catch (Exception e)
		{
			System.out.println("Error Occurred!!!! \n"
                               + e.getMessage());			

		}
	}
	public void printEvaluation()
	{
		System.out.println(_evaluation.toSummaryString(
                "\nResults", false));
	}




    public static void main(String[] args) {

	J48Classifier test=new 	J48Classifier();
	//String file_name="C:\\Users\\User\\wekafiles\\packages\\wekaDeeplearning4j\\datasets\\nominal\\iris.arff";
	String file_name="./iris.arff";
	test.getFile(file_name);
	test.evaluationOutcome();
	test.printEvaluation();

    }

}

