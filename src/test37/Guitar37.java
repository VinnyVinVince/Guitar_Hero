package test37;

public class Guitar37 implements Guitar
{
	private GuitarString[] strings;
	private int time;
	
    public static final int STRING_AMOUNT = 37;
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    
    public Guitar37()
    {
    	this.strings = new GuitarString[STRING_AMOUNT];
    	
    	for (int i = 0; i < STRING_AMOUNT; i++)
    		this.strings[i] = new GuitarString(440. * Math.pow(2., (i-24.)/12.));
    }
    
    public void playNote(int pitch)
    {
    	int index = pitch + 24;
    	
    	if (index > -1 && index < STRING_AMOUNT)
    		this.strings[index].pluck();
    }
    
    public boolean hasString(char key)
    {
    	return KEYBOARD.contains("" + Character.toLowerCase(key));
    }
    
    public void pluck(char key)
    {
    	if (!hasString(key))
    		throw new IllegalArgumentException();
    	
    	this.strings[KEYBOARD.indexOf(Character.toLowerCase(key))].pluck();
    }
    
    public double sample()
    {
    	double sampleSum = 0;
    	
    	for (GuitarString string : this.strings)
    		sampleSum += string.sample();
    	
    	return sampleSum;
    }
    
    public void tic()
    {
    	this.time++;
    	
    	for (GuitarString string : this.strings)
    		string.tic();
    }
    
    public int time()
    {
    	return this.time;
    }
}