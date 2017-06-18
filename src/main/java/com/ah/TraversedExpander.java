package com.ah;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by andrew on 5/16/15.
 */
public class TraversedExpander
{
    public List<TraversedNode> captureExpansion( int depth, TraversedNode startingPoint )
    {
        System.out.println( "Starting at " + startingPoint );

        int currentDepth = 1;

        if (startingPoint.isVowel())
        {
            startingPoint.incrementVisitedCount();
        }

        List< TraversedNode > nodes = Lists.newArrayList( startingPoint );
        while( currentDepth <= depth )
        {
            nodes = nodes.stream( )
                         .map( this::expand )
                         .reduce( Lists.newLinkedList( ), ( accumulator, addend ) -> { accumulator.addAll( addend ); return accumulator; } );

            System.out.println( "At depth " + currentDepth + " has node amount " + nodes.size() );
            ++currentDepth;
        }


        int min = nodes.stream().map( node -> node.getVowelsVisited() ).min(Integer::min).get();
        System.out.println( "Min VOWELS visited is " + min );
        Set<Character> distinct = nodes.stream().map( node -> node.getCharacter() ).collect( Collectors.toSet() );
        System.out.println( "Distinct " + distinct );


        return nodes;
    }


    private List< TraversedNode > expand( TraversedNode startingPoint )
    {
        List< Character > transitions = startingPoint.getVowelsVisited() > 1 ? Transitions.getNVTransitions( startingPoint.getCharacter( ) )
                                                                             : Transitions.getTransitions( startingPoint.getCharacter( ) );

        return transitions.stream()
                          .map( ch -> new TraversedNode( ch, startingPoint.getVowelsVisited() + ( Transitions.isVowel( startingPoint.getCharacter() ) ? 1 : 0 ) ) )
                          .collect( Collectors.toList( ) );
    }



}
