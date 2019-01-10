package PackmanGame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Server {
	private double Score_First=0;
	private double Score_Second=0;
	private double My_Max_Score=0;
	private String CodeMap="";
	private String MapName="";
	
	/**
	 * This class calculate the statistics of the games for the game we played. תפתח את הגיטהאב שלך
	 */
	public Server(int Map) {
		if(Map==2128259830) {
			CodeMap="2128259830";
			MapName="Ex4_OOP_example1";
		}else if(Map==1149748017) {
			CodeMap="1149748017";
			MapName="Ex4_OOP_example2";
		}else if(Map==683317070) {
			CodeMap="683317070";
			MapName="Ex4_OOP_example3";
		}else if(Map==1193961129) {
			CodeMap="1193961129";
			MapName="Ex4_OOP_example4";
		}else if(Map==1577914705) {
			CodeMap="1577914705";
			MapName="Ex4_OOP_example5";
		}else if(Map==1315066918) {
			CodeMap="1315066918";
			MapName="Ex4_OOP_example6";
		}else if(Map==1377331871) {
			CodeMap="1377331871";
			MapName="Ex4_OOP_example7";
		}else if(Map==-1377331871) {
			CodeMap="-1377331871";
			MapName="Ex4_OOP_example8";
		}else if(Map==919248096) {
			CodeMap="919248096";
			MapName="Ex4_OOP_example9";
		}	
	}
	public void GiveMeStatstic() {
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop"; //?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String jdbcUser="student";
		String jdbcPassword="student";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
			Statement statement = connection.createStatement();
			String allCustomersQuery = "SELECT * FROM logs;";
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
			resultSet=statement.executeQuery(allCustomersQuery);
			while(resultSet.next())
			{
				//פה אני בודק רק את הציונים שלנו ולוקח את הכי גבוהה
				if(resultSet.getInt("FirstID")==203640768 && resultSet.getInt("SecondID")==308529296) 
					if(resultSet.getString("SomeDouble").equals(CodeMap)) 
						if(My_Max_Score<resultSet.getInt("Point")) 
							My_Max_Score=resultSet.getInt("Point");
			}
			resultSet=statement.executeQuery(allCustomersQuery);
			while(resultSet.next())
			{ 
				if(resultSet.getString("SomeDouble").equals(CodeMap)) 
					if(Score_First<resultSet.getInt("Point")) {
						Score_Second=Score_First;
						Score_First=resultSet.getInt("Point");
					}
			}
			resultSet.close();		
			statement.close();		
			connection.close();		
		}

		catch (SQLException sqle) {}

		catch (ClassNotFoundException e) {}
	}
	public double getMy_Max_Score() {
		return My_Max_Score;
	}
	public double getScore_First() {
		return Score_First;
	}
	public double getScore_Second() {
		return Score_Second;
	}
	public String getMapName() {
		return MapName;
	}
}