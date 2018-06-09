//+3x +3y -4z = 2
import java.util.*;
class Equation
{    
    private String EQ;
    Variable FVar;
    double satSol;    
    public Equation()
    {
        FVar = null;
        satSol = 0.0d;
        EQ = "";
    }
    public void input()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Equation: ");
        EQ = sc.nextLine();
        EQ = EQ.trim();
    }
    public void standardizeEq()
    {
        for(int i = 1; i < EQ.length(); i++)
        {
            if(Character.isLetter(EQ.charAt(i)) == true)
            {
                if(Character.isDigit(EQ.charAt(i - 1)) == false)
                {
                    EQ = EQ.substring(0, i) + "1" + EQ.substring(i);
                }
            }
        }
        if(Character.isLetter(EQ.charAt(0)) == true)
        EQ = "+1" + EQ;
        else if(Character.isDigit(EQ.charAt(0)) == true)
        EQ = "+" + EQ;
        else if(Character.isLetter(EQ.charAt(1)) == true)                   //Doubt.!
        EQ = EQ.charAt(0) + "1" + EQ.substring(2);  
        
        //Putting Proper Spaces.
        
        for(int i = 0; i < EQ.length() - 2; i++)
        {
            if(Character.isLetter(EQ.charAt(i)) == true)
            {
                if(EQ.charAt(i + 1) != ' ')
                {
                    EQ = EQ.substring(0, i + 1) + " " + EQ.substring(i + 1);
                }
            }
        }
    }
    
    public void createEq()
    {
        StringTokenizer st = new StringTokenizer(EQ);
        int c = st.countTokens();
        for(int i = 1; i <= c - 1; i++)
        {
            String v = st.nextToken();   
            String coef = v.substring(1, v.length() - 1);
            Variable V = new Variable(v.charAt(0), Double.parseDouble(coef), v.charAt(v.length() - 1));
            addVar(V);
        }
    }
    public void addVar(Variable V)
    {      
        Variable p = FVar;
        if(FVar == null)
        FVar = V;
        else
        {
            while(p.nextVar != null)
            p = p.nextVar;
            p.nextVar = V;
        }
    }
    
    public void getSatSol()
    {
        int i;
        for(i = 0; i < EQ.length(); i++)
        {
            if(EQ.charAt(i) == '=')
            break;
        }
        satSol = Double.parseDouble(EQ.substring(i + 1));
    }  
    public void display()
    {
        System.out.println(EQ);
    }
    public void getEquation()
    {        
        input();
        standardizeEq();
        createEq();
        getSatSol();
    }
}
