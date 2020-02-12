// see https://dzone.com/articles/using-h2o-models-into-java-for-scoring-or-predicti

import hex.genmodel.easy.RowData;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.prediction.*;
import hex.genmodel.MojoModel;

public class App {

	public static void main(String[] args) throws Exception {

		EasyPredictModelWrapper model_orig = new EasyPredictModelWrapper(MojoModel.load(
				"src/main/resources/XGBoost_grid__1_AutoML_20200212_210321_model_3.zip"));
		{
			RowData rowTrain3 = new RowData();
			rowTrain3.put("Pclass", "3");
			rowTrain3.put("Sex", "female");
			rowTrain3.put("Age", "26.0");
			rowTrain3.put("SibSp", "0");
			rowTrain3.put("Parch", "0");
			rowTrain3.put("Fare", "7.9250");
			rowTrain3.put("Embarked", "S");
			// Survived 1

			RowData rowTest896 = new RowData();
			rowTest896.put("Pclass", "3");
			rowTest896.put("Sex", "female");
			rowTest896.put("Age", "22.0");
			rowTest896.put("SibSp", "1");
			rowTest896.put("Parch", "1");
			rowTest896.put("Fare", "12.2875");
			rowTest896.put("Embarked", "S");
			// Survived 1
			
			BinomialModelPrediction p = model_orig.predictBinomial(rowTrain3);
			printProbabilities("ID 3	", p);
			
			p = model_orig.predictBinomial(rowTest896);
			printProbabilities("ID 896	", p);
		}
	}
	
	static void printProbabilities(String message, BinomialModelPrediction p) {
		System.out.println(message);
		for (int i = 0; i < p.classProbabilities.length; i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(p.classProbabilities[i]);
		}
		System.out.println("");
		System.out.println("survived? " + (p.classProbabilities[1] > 0.5));
		System.out.println("");
	}
}
