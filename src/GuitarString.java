/**
 * @author Vincent Do <Vincent.Do@EastsideCatholicSchool.org>
 * @version 1.0 (10/2/2019)
 * 
 * specific-frequency model of a vibrating guitar string that works on a ring buffer
 */

import java.util.*;

public class GuitarString
{
	private Queue<Double> ringBuffer;
	
	public static final double ENERGY_DECAY_FACTOR = 0.996;
	
	/**
	 * precondition:	@param frequency > 0, StdAudio.SAMPLE_RATE / frequency >= 2
	 * 
	 * postcondition:	constructed GuitarString object with ring buffer Queue filled with N
	 * 					(sample rate / frequency) elements, each are 0.0
	 * 
	 * @throws IllegalArgumentException if @param frequency < 1 or N (Queue size) < 2
	 */
	public GuitarString(double frequency)
	{
		if (frequency <= 0)
			throw new IllegalArgumentException("frequency must be greater than zero");
		
		int bufferSizeN = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);
		
		if (bufferSizeN < 2)
			throw new IllegalArgumentException("ring buffer must have at least two elements");
		
		this.ringBuffer = new LinkedList<Double>();
		
		for (int i = 0; i < bufferSizeN; i++)
			this.ringBuffer.add(0.);
	}
	
	/**
	 * precondition:	@param init.length >= 2
	 * 
	 * postcondition:	constructed GuitarString object with ring buffer Queue filled with elements from
	 * 					@param init
	 * 
	 * @throws IllegalArgumentException if @param init has less than two elements
	 */
	public GuitarString (double[] init)
	{
		if (init.length < 2)
			throw new IllegalArgumentException("array must have at least two elements");
		
		this.ringBuffer = new LinkedList<Double>();
		
		for (double element : init)
			this.ringBuffer.add(element);
	}
	
	/**
	 * precondition:	GuitarString object constructed
	 * 
	 * postcondition:	all ring buffer Queue elements replaced with random values between -0.5 inclusive
	 * 					and 0.5 exclusive (-0.5 <= value < 0.5)
	 */
	public void pluck()
	{
		Random generate = new Random();
		
		for (int i = 0; i < this.ringBuffer.size(); i++)
		{
			this.ringBuffer.remove();
			this.ringBuffer.add(generate.nextDouble() - 0.5);
		}
	}
	
	/**
	 * precondition:	GuitarString object constructed
	 * 
	 * postcondition:	removes front of ring buffer Queue, adds (average of removed element and new front
	 * 					element) times energy decay factor
	 */
	public void tic()
	{
		this.ringBuffer.add(
				(this.ringBuffer.remove() + this.ringBuffer.peek()) / 2 * ENERGY_DECAY_FACTOR);
	}
	
	/**
	 * precondition:	GuitarString object constructed
	 * 
	 * postcondition:	@returns front element of ring buffer Queue
	 */
	public double sample()
	{
		return this.ringBuffer.peek();
	}
}