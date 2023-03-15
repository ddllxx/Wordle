package comp1721.cwk1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


public class Game {
        private int gameNumber;
        private String target;
        int begin = 3;
        private int jue = 3;
        private int numtime = 0;
        private String become;
        private String rem = "";
        private int temp1=0;
    private int temp2=0;
    private double temp11 = 0;
    private double temp22 = 0;
    private int temp3=0;
    private int max=0;
    private double output;
    private List<Integer> guesstime = new ArrayList<>();
    private List<Integer> juewin = new ArrayList<>();
    private ChartFrame frame1;
    private String readget;
    private List<Integer> xdic = new ArrayList<>();
    // TODO: Implement constructor with String parameter
    Game(String game_word) throws GameException, FileNotFoundException {
        WordList wordList = new WordList(game_word);
        int choose;
        choose =  (int) (LocalDate.now().toEpochDay()-LocalDate.of(2021,6,19).toEpochDay());
        target = wordList.getWord(choose);
        gameNumber = choose;
    }
    // TODO: Implement constructor with int and String parameters
    Game(int num,String game_word) throws GameException, FileNotFoundException {
        gameNumber = num;
        WordList wordList = new WordList(game_word);
        target = wordList.getWord(gameNumber);
    }
    // TODO: Implement play() method
    public void play()
    {
    int i;
    for(i = 1;i<=6;i++)
    {
        System.out.printf("Enter guess (%d/6)",numtime+1);
        Guess guess = new Guess(i);
        become = guess.compareWith(target);
        rem += become;
        rem += "\n";
        System.out.println(become);
        numtime++;
        if(guess.matches(target))
        {
            if(i==1)
            {
                jue = 2;
                System.out.println("Superb - Got it in one!");
                break;
            }
                jue = 2;
                System.out.println("Well done!");
                break;
            }
    }
    if(jue == 3)
    {
        System.out.println("That was a close call!");
    }

}
    // TODO: Implement save() method, with a String parameter
    public String save(String file) throws IOException
    {
        File fn = new File("build/history.txt");
        if(!fn.exists())
        {
            try{
                fn.createNewFile();
            }catch(IOException e)
            {e.printStackTrace();
        }}
        FileWriter fh = new FileWriter(fn,true);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        fh.write("Time: "+format.format(date)+"\n");
        fh.write("GameNumber: "+gameNumber+"\n");
        fh.write("PlayedWord: "+ target+ "\n");
        if(jue == 3)
        {fh.write("IsWon: "+ "false" +"\n");}
        if(jue == 2)
        {
            fh.write("IsWon: "+ "win" +"\n");
        }
        fh.write("GuessNumber: " +numtime +"\n");
        fh.close();
        FileWriter fw = new FileWriter(file,true);
        if(begin == 2){
        fw.write("-------------------------------------------------------\n");
        fw.write("model 2 for color blind!\n");
        fw.write(rem);}
        if(begin == 3)
        {
            fw.write("-------------------------------------------------------\n");
            fw.write("model 3 for normal people!\n");
            fw.write(rem);
        }
        fw.write("-------------------------------------------------------\n");
        fw.close();
        File fv = new File("build/history.txt");
                if(!fv.exists())
                {
                    return"finish";
                }
        BufferedReader read = null;
        try{
            read = new BufferedReader(new FileReader("build/history.txt"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(true)
        {
            readget = read.readLine();
            if(readget == null)
            {
                break;
            }
            temp1++;
            readget = read.readLine();
            readget = read.readLine();
            readget = read.readLine();
            if(readget.contains("win"))
            {
                temp2++;
                temp3++;
                juewin.add(1);
                if(max<=temp3)
                {
                    max = temp3;
                }
                readget = read.readLine();
                guesstime.add(Integer.parseInt(readget.replace("GuessNumber: ","")));
            }
            if(readget.contains("false"))
            {
                temp3 = 0;
                juewin.add(0);
                readget = read.readLine();
                guesstime.add(Integer.parseInt(readget.replace("GuessNumber: ","")));
            }

        }
        System.out.println("----------------------------------------------------------------");
        System.out.printf("Number of games played: %d\n",temp1);
        temp22 = (double)temp2;
        temp11 = (double)temp1;
        output = temp22/temp11;
        NumberFormat num = NumberFormat.getPercentInstance();
        String out = num.format(output);
        System.out.printf("Percentage of games that were wins: %s\n",out);
        System.out.printf("Length of the current winning streak: %d\n",temp3);
        System.out.printf("Longest winning streak: %d\n",max);
        drewpicture();
        System.out.println("----------------------------------------------------------------");
        return "finish";
    }
    public CategoryDataset getDataSet(){
        int i;
        for(i=1;i<= guesstime.size();i++)
        {
            xdic.add(i);
        }
        int[] get1 = new int[guesstime.size()];
        int j=0;
        for(Integer num : guesstime)
        {
            get1[j] = num;
            j++;
        }
        int[] get3 = new int[juewin.size()];
        j = 0;
        for(Integer num1 : juewin)
        {
            get3[j] = num1;
            j++;
        }
        j = 0;
        int[] get2 = new int[xdic.size()];
        for(Integer num2:xdic)
        {
            get2[j] = num2;
            j++;
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(i=0;i<get2.length;i++)
        {
            if(get3[i] == 1)
            {dataset.addValue(get1[i],"win" , ""+get2[i]);}
            if(get3[i] == 0)
            {
                dataset.addValue(get1[i],"Lose" ,""+get2[i]);
            }
        }
        return dataset;
    }
    public void drewpicture() {
        CategoryDataset dataset = getDataSet();
        JFreeChart chart = ChartFactory.createBarChart3D(
                "guess distribution",
                "guessnumber",
                "playtime",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );
        CategoryPlot plot=chart.getCategoryPlot();
        File fb = new File("build/1.png");
        try {
            ChartUtilities.saveChartAsJPEG(fb, chart, 600, 500);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        frame1 = new ChartFrame("guess",chart);
        frame1.setVisible(true);
        frame1.pack();


    }
    }
