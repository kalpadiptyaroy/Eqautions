class Variable
{
    char c, sign; double coeff; Variable nextVar;
    public Variable()
    {
        sign = c = '\u0000';   coeff = 0.0d;
        nextVar = null;
    }
    public Variable(char sign, double coeff, char c)
    {
        this.sign = sign;   this.c = c;  this.coeff = coeff;
        nextVar = null;
    }
    public double getCoeff()
    {
        return coeff;
    }
    public char getVar()
    {
        return c;
    }
    public char getSign()
    {
        return sign;
    }    
}
