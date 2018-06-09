import java.util.*;
class Matrix
{
    double a[][];int m, n, k = 0; char c[][];
    public Matrix()
    {
        m = n = k = 0;        
    }
    public Matrix(int m, int n)
    {
        this.m = m;   this.n = n;    a = new double[m][n]; 
        c = new char[m][n];    k = 0;
    }
    public void addData(int x)
    {
        a[k / m][k++ % n] = x;
    }
    public void addData(char x)
    {
        c[k / m][k++ % n] = x;
    }    
    public void input()
    {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < m; i++)        
            for(int j = 0; j < n; j++)
            {
                System.out.print("Enter the No: ");
                a[i][j] = sc.nextInt();
            }        
    }
    public void display()
    {        
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
                System.out.print(a[i][j] + "\t");
            System.out.println();
        }
        
    }
    public boolean canCross(Matrix B)
    {
        if(this.n == B.m)
            return true;
        else 
            return false;
    }
    public Matrix cross(Matrix B) //Working correctly.
    {
        Matrix R = new Matrix(B.m, B.n);
        if(this.canCross(B) == false)
            System.out.println("Matrix Multiplication is not possible!");
        else
        {            
            int i = 0, j = 0, k = 0;
            while(i < (R.m * R.n))
            {                
                if(B.n != 1)
                R.a[i % R.m][i / R.n]+= (this.a[j / this.m][j % this.n]) * (B.a[k % B.m][k / (B.n * B.n)]);
                else
                R.a[i % R.m][0]+= (this.a[j / this.m][j % this.n]) * (B.a[k % B.m][0]);
                j++; k++;
                if(j % this.n == 0)                
                    i++;
                if(j % (this.n * this.n) == 0)
                    j = 0;
            }            
        }        
        return R;
    }

    public double det() // Working Correctly!
    {    
        
        //We are always expanding by the first row.
        double d = 0;
        if(this.m == 2 && this.n == 2)
        return (this.a[0][0] * this.a[1][1]) - (this.a[0][1] * this.a[1][0]);
        else
        {
            for(int i = 0; i < this.n; i++)                   
                d = d + (int)Math.pow(-1, i)*(this.a[0][i] * this.cofac(0, i).det());            
            return d;
        }        
    }
    public Matrix cofac(int fbi, int fbj) //this is working!
    {
        Matrix ob = new Matrix(this.m - 1, this.n - 1); int p = 0;
        for(int i = 0; i < this.m; i++)
        {
            for(int j = 0; j < this.n; j++)
            {
                if(j != fbj && i != fbi)
                    ob.a[p / ob.m][p++ % ob.n] = this.a[i][j];  
            }
        }
        return ob;
    }    
    public Matrix transpose()
    {
        Matrix ob = new Matrix(this.n, this.m);
        for(int i = 0; i < this.m; i++)
        {
            for(int j = 0; j < this.n; j++)
            {
                ob.a[j][i] = this.a[i][j];
            }
        }
        return ob;
    }    
    public Matrix adj() //Working correctly!
    {
        Matrix ob = new Matrix(this.m, this.n);
        int p = 0;
        while(p <= (this.m * this.n) - 1)
        {
            int i = p / this.m, j = p % this.n;
            ob.a[i][j] = (int)Math.pow(-1, p) * (this.cofac(i, j).det());
            p++;
        }
        return ob.transpose();
    }
    public Matrix inverse() //working properly. 
    {
        Matrix ob = new Matrix(this.m, this.n);
        if(this.det() == 0)
        System.out.println("Inverse Does not exists. Determinant is Zero");
        else
        {
            ob = this.adj(); double d = this.det();
            for(int i = 0; i < ob.m; i++)
                for(int j = 0; j < ob.n; j++)
                    ob.a[i][j] = ob.a[i][j] / d;                    
        }
        return ob;
    }
    public Matrix solveMartins(Matrix B) 
    {
                                                        
        //Works Fine For 3 Unknown Systems.        
        double d = this.det();
        Matrix ob = new Matrix(m,n); ob.inverse().cross(B);        
        return ob;
    }    
    public void doAssign(Matrix X)
    {
        this.m = X.m;
        this.n = X.n;
        this.k = X.k;
        for(int i = 0; i < m; i++)        
            for(int j = 0; j < n; j++)            
                this.a[i][j] = X.a[i][j];            
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                this.c[i][j] = X.c[i][j];
    }
    public static void main()
    {
        Matrix ob = new Matrix(4, 4);
        ob.input();
        System.out.println(ob.det());   
    }
}        
