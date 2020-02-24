/**
 * @author Vincent Do <Vincent.Do@EastsideCatholicSchool.org>
 * @version 1.0 (10/7/2019)
 * 
 * simulated guitar with 37 strings, keeps track of operations, case-insensitive key handling
 */

public class Guitar37 implements Guitar
{
	private GuitarString[] strings;
	private int time;				// auto-initialized to 0
	
    public static final int STRING_AMOUNT = 37;
    public static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    
    /**
     * precondition:	n/a
     * 
     * postcondition:	constructed Guitar37 object with this.strings array filled with 37 GuitarStrings
     * 					of varying frequency
     */
    public Guitar37()
    {
    	this.strings = new GuitarString[STRING_AMOUNT];
    	
    	for (int i = 0; i < STRING_AMOUNT; i++)
    		this.strings[i] = new GuitarString(440. * Math.pow(2., (i-24.)/12.));
    }
    
    /**
     * precondition:	-24 <= @param pitch <= 12, will not throw error if not met
     * 
     * postcondition:	pluck GuitarString in this.strings corresponding to the inputed pitch,
     * 					does nothing if pitch is not within Guitar37 pitch range
     */
    public void playNote(int pitch)
    {
    	int index = pitch + 24;
    	
    	if (index > -1 && index < STRING_AMOUNT)
    		this.strings[index].pluck();
    }
    
    /**
     * precondition:	Guitar37 object constructed
     * 
     * postcondition:	@returns true if @param key corresponds to a character in KEYBOARD,
     * 					@returns false if not
     */
    public boolean hasString(char key)
    {
    	return KEYBOARD.contains("" + Character.toLowerCase(key));
    }
    
    /**
     * precondition:	@param key is a character in KEYBOARD
     * 
     * postcondition:	plucks GuitarString in this.strings corresponding to @param key
     * 
     * @throws IllegalArgumentException if @param key does not match a character in KEYBOARD
     */
    public void pluck(char key)
    {
    	if (!hasString(key))
    		throw new IllegalArgumentException("key must be valid character contained in KEYBOARD");
    	
    	this.strings[KEYBOARD.indexOf(Character.toLowerCase(key))].pluck();
    }
    
    /**
     * precondition:	Guitar37 object constructed
     * 
     * postcondition:	@returns sum of all GuitarString sample calls in this.strings
     */
    public double sample()
    {
    	double sampleSum = 0;
    	
    	for (GuitarString string : this.strings)
    		sampleSum += string.sample();
    	
    	return sampleSum;
    }
    
    /**
     * precondition:	Guitar37 object constructed
     * 
     * postcondition:	increments this.time, calls tic on each GuitarString in this.strings
     */
    public void tic()
    {
    	this.time++;
    	
    	for (GuitarString string : this.strings)
    		string.tic();
    }
    
    /**
     * precondition:	Guitar37 object constructed
     * 
     * postcondition:	@returns this.time (amount of times tic() method is called)
     */
    public int time()
    {
    	return this.time;
    }
}