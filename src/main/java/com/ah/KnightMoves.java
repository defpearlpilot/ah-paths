package com.ah;


import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Map;


/**
 * Created by andrew on 5/17/15.
 */
public class KnightMoves
{
    private static void runMemoizingExpanderA(Character ch)
    {
        Map<Integer, BigDecimal> depthPathMap = Maps.newHashMap( );

        for (Integer depth: new Integer[] {2, 3, 10,16,24,28, 32, 64})
        {
            Stopwatch time1 = Stopwatch.createStarted();
            MemoRecursiveExpander memo = new MemoRecursiveExpander( depth );
            BigDecimal paths = memo.countPaths( ch );
            depthPathMap.put( depth, paths );

            System.out.println( "At depth " + depth + " pathsForSequenceLength " + paths + " took " + time1.toString( ) );
        }
    }


    private static void captureInput()
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            System.out.println( "Enter the starting point: " );
            Character startNode = null;

            while ( true )
            {
                String s = br.readLine();
                if ( s.length() > 1 )
                {
                    System.out.println( "Input must be one of: " + Transitions.getValidNodes( ) );
                    continue;
                }

                startNode = s.charAt( 0 );
                if ( Transitions.isValidNode( startNode ) )
                {
                    break;
                }

                System.out.println( "Input must be one of: " + Transitions.getValidNodes( ) );
            }

            System.out.println( "Enter the sequence length: " );

            int sequenceLength = 0;
            while ( true )
            {
                String s = br.readLine();

                try
                {
                    sequenceLength = Integer.parseInt( s );
                    break;
                }
                catch ( NumberFormatException nfe )
                {
                    System.out.println( "Please enter an integer for the sequence amount!" );
                }
            }

            MemoRecursiveExpander memoRecursiveExpander = new MemoRecursiveExpander( sequenceLength );
            BigDecimal numPaths = memoRecursiveExpander.countPaths( startNode );
            System.out.println( "The number of pathsForSequenceLength is: " + numPaths );
        }
        catch ( IOException e )
        {
            e.printStackTrace( );
        }
    }

    public static void main(String[] args)
    {
        captureInput();
    }
}
