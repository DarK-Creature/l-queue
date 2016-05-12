import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.concurrent.TimeUnit;
import org.junit.*;
//import static org.junit.Assert.*;
import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;


public class EestipiirCheckerController {

	 private WebDriver driver;
	  private String baseUrl;
	  //private boolean acceptNextAlert = true;
	  //private StringBuffer verificationErrors = new StringBuffer();
	  
	  
	  @Before
	  public void setUp(){
	    driver = new FirefoxDriver();
	    baseUrl = "https://www.eestipiir.ee/yphis/index.action?request_locale=ru";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  @Before
	  public void setUpOcered(){
	    driver = new FirefoxDriver();
	    baseUrl = "https://www.eestipiir.ee/yphis/borderQueueInfo.action?request_locale=ru";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  @Test
	  public void test1(){
		  int delay = (int) (8 + Math.random()*5);
		  int count = 1;
		  int round = 1;
		  int CarsAtStart = 0;
		  int carsPass = 0;
		  String text="";
		  long startTime = System.currentTimeMillis();
		  
		  while(!text.contains("Вас вызывают на границу!")){
		  
		  
		  String category = tf1.getText();
		  String mm = tf2.getText();
		  String dd = tf3.getText();
		  String nr = tf4.getText();
		 // String vqzov_na_granicu = "Called to border! \n Вас вызывают на границу! \n Teid kutsuti piirile!";
		  
		 	  
	    driver.get(baseUrl);
	    driver.findElement(By.id("headerQueueNumberCategory")).clear();
	    driver.findElement(By.id("headerQueueNumberCategory")).sendKeys(category);
	    driver.findElement(By.id("headerQueueNumberMM")).clear();
	    driver.findElement(By.id("headerQueueNumberMM")).sendKeys(mm);
	    driver.findElement(By.id("headerQueueNumberDD")).clear();
	    driver.findElement(By.id("headerQueueNumberDD")).sendKeys(dd);
	    driver.findElement(By.id("headerQueueNumberRandom")).clear();
	    driver.findElement(By.id("headerQueueNumberRandom")).sendKeys(nr);
	    driver.findElement(By.id("searchButton")).click();
	   
	    text = driver.findElement(By.cssSelector("div.article")).getText();
	   System.out.println(text+ "\n");
	   
	    if(text.contains("Вас вызывают на границу!")){
	    	Sound.playSound("vqzov.wav").join();
	    	System.out.println("Вас вызывают на границу!");
	    	break;}
	    else if(text.contains("Бронирование с указанным ID кодом не найдено")){
	    	System.out.println("Бронирование с указанным ID кодом не найдено");
	    	break;}
	    else if(text.contains("в очереди перед Вами")){
	    	String var=driver.findElement(By.xpath("//strong[2]")).getText();
	    	int varToInt = Integer.parseInt(var);
	    	if(varToInt<60&&count==1&&check30.isSelected()){
	    		Sound.playSound("60.wav").join();
	    		delay=(int) (4 + Math.random()*3);
	    		count++;
	    	}
	    	else if(varToInt<30&&count==2&&check30.isSelected()){
	    		Sound.playSound("30.wav").join();
	    		delay=(int) (2 + Math.random()*3);
	    		count++;
	    	}
	    	else if(varToInt<10&&count==3&&check10.isSelected()){
	    		Sound.playSound("10.wav").join();
	    		delay=(int) (1 + Math.random()*2);
	    		count++;
	    	}
	    	
	    if(round == 1){
	    	CarsAtStart = varToInt;
	    	}
	    else{
	    	carsPass = CarsAtStart - varToInt;
	    }
	    
	    int totalMins = (int)((System.currentTimeMillis() - startTime)/60000);
	    int totalHours = totalMins/60;
	    
	    if(totalHours == 0){
	    	avtoVchas.setText(" ждите...");
	    }
	    else{
	    	int carsInHour = carsPass/totalHours;
	    	String CarsInH = Integer.toString(carsInHour);
	    	avtoVchas.setText(CarsInH + " авто/час");
	    }
	    

    	int vremjaOzidanija = varToInt*(carsPass/totalMins/60);
    	String vremjaOz = Integer.toString(vremjaOzidanija);
    	ozidanie.setText(vremjaOz +" ч.");
    	
	    round++;
	    	
	    	System.out.println(varToInt);}
	    else{
	    	System.out.println("ne Rabotaet");
	    	break;
	    	}
	    try {
	        
	        TimeUnit.MINUTES.sleep(delay);
	       
	    } catch (InterruptedException e) {
	        //Handle exception
	    }
	    //driver.quit();
		  }
		  
	  }
	
	  
	  public void oceredTest(){
		  avtoVchas.setText("Hello, World123.");
		  int delay = (int) (1);
		  String data="";
		  while(true){
			 
		  
		  String vremja = tf5.getText();
		  int zelaemoeVremja = Integer.parseInt(vremja);	
		  System.out.println(zelaemoeVremja);
		  
		  String curTime=new java.text.SimpleDateFormat("HH").format(java.util.Calendar.getInstance().getTime());
		  int curentHour = Integer.parseInt(curTime);
		  System.out.println(curentHour);
		  
		driver.get(baseUrl);  
	    data = driver.findElement(By.id("lql-1")).getText();
	    String text = data.replaceAll("[^0-9]+", "");
	    int vremOzid = Integer.parseInt(text);
	    System.out.println(vremOzid);
	   
	    if(curentHour>zelaemoeVremja){
	    	System.out.println('>');
	    	if(curentHour-24+vremOzid>=zelaemoeVremja){
	    	Sound.playSound("vqzov.wav").join();
	    	break;}}
	    else if(curentHour<zelaemoeVremja){
	    	System.out.println('<');
	    	if(curentHour+vremOzid>=zelaemoeVremja){
	    	Sound.playSound("vqzov.wav").join();
	    	break;}}
	    else{System.out.println('=');
	    	Sound.playSound("vqzov.wav").join();
	    	break;
	    	}
	    try {
	        
	        TimeUnit.MINUTES.sleep(delay);
	       
	    } catch (InterruptedException e) {
	        //Handle exception
	    }
	    
		  }
		  
	  }
	  
	@FXML
    private TextField tf1;
	
	@FXML
    private TextField tf2;
	
	@FXML
    private TextField tf3;
	
	@FXML
    private TextField tf4;
	
	@FXML
    private TextField tf5;
	
	@FXML
    private CheckBox check10;
	
	@FXML
    private CheckBox check30;
	
	@FXML
    private CheckBox check60;
	
	@FXML
    private Label avtoVchas;
	
	@FXML
    private Label ozidanie;
	
	@FXML
    private void handleOk() {
        
            setUp(); 
            test1();
    }
	
	@FXML
    private void ocered() {
		 
            setUpOcered(); 
            oceredTest();
    }
	
	// Reference to the main application.
    private MainApp MainApp;
    
    public EestipiirCheckerController() {
}
    public void setMainApp(MainApp MainApp) {
        this.MainApp = MainApp;

    }
}

