import java.util.*;
class Solve
{
    Matrix A, B; Matrix D[];    Equation ob[];    int n;
    public Solve()
    {
        A = new Matrix();  B = new Matrix();   n = 0;
    }

    public Solve(int n)
    {
        A = new Matrix(n, n);  B = new Matrix(n, 1);
        D = new Matrix[n];     this.n = n;
        ob= new Equation[n];        
    }

    public void inputFromFile()
    {

    }

    public void input()
    {       
        for(int i = 0; i < n; i++)                   
        {
            ob[i] = new Equation();  ob[i].getEquation();  
        }
    }

    public void putInMatrix()
    {        
        for(int i = 0; i < n; i++)
        {
            D[i] = new Matrix(n, n); int k = 0;
            Variable p = ob[i].FVar;
            while(p != null)
            {
                if(p.sign == '-')                
                    A.a[i][k++] = p.coeff * (-1);                
                else                
                    A.a[i][k++] = p.coeff;                   
                p = p.nextVar;
            }
            B.a[i][0] = ob[i].satSol;              
        } 
        for(int i = 0; i < n; i++)
            D[i].doAssign(A);
        for(int i = 0; i < n; i++)        
            for(int j = 0; j < n; j++)            
                D[i].a[j][i] = ob[j].satSol;         
    }

    public void getMartinsSolutions() 
    {
        //not effieciently for more than 3 unknowns.
        //Cannot Detect the Error Yet!
        //Works Fine For 3 Unknown Systems.

        System.out.println("\t THE UNIQUE SOLUTION OF THE SYSTEM OF EQUATIONS ARE: ");
        Matrix X = A.solveMartins(B);    Variable p = ob[0].FVar;       int k = 0;
        while(p != null)
        {
            System.out.println(p.c + "  =  " + X.a[k++][0]);
            p = p.nextVar;
        }
    } 

    public void showEquations()
    {
        System.out.println("\t YOUR GIVEN EQUATION SYSTEM IS: "); 

        for(int i = 0; i < n; i++)
            ob[i].display();
    }

    public void getCramersSolutions()
    {
        //Works Fantastic.
        //For any no. of Unknowns!        

        System.out.println("\t THE UNIQUE SOLUTION OF THE SYSTEM OF EQUATIONS ARE: ");

        Matrix X = new Matrix(n, 1);
        if(A.det() == 0)
            System.out.println("Determinant is Zero. System has Infinite No. of Solutions");
        else
        {
            for(int i = 0; i < n; i++)        
                X.a[i][0] = D[i].det() / A.det();        
            Variable p = ob[0].FVar; int k = 0;
            while(p != null)
            {
                System.out.println(p.c + "  =  " + X.a[k++][0]);
                p = p.nextVar;
            }
        }
    }

    public static void main()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter The No. of Eqautions: ");
        int n = sc.nextInt();
        Solve ob = new Solve(n);
        ob.input();      ob.putInMatrix();    ob.showEquations();        
        System.out.println("Cramers' Rule");
        ob.getCramersSolutions();               
    }
}
