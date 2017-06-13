package com.kcg;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Initial class used for experimentation.
 */
@Deprecated
public class Expander
{
    public List<Character> expandToDepth( int depth, Character startingPoint )
    {
        int currentDepth = 1;

        int vowelsVisited = Transitions.isVowel( startingPoint ) ? 1 : 0;

        List< TraversedNode > nodes = Lists.newArrayList( new TraversedNode( startingPoint, vowelsVisited ) );
        while( currentDepth <= depth )
        {
            nodes = nodes.stream( )
                         .map( node -> expand( node ) )
                         .reduce( Lists.newLinkedList( ), ( accumulator, addend ) -> { accumulator.addAll( addend ); return accumulator; } );

            ++currentDepth;
        }


        int min = nodes.stream().map( node -> node.getVowelsVisited() ).min(Integer::min).get();
        Set<Character> distinct = nodes.stream( ).map( node -> node.getCharacter( ) ).collect( Collectors.toSet( ) );

        System.out.println( "Min vowels visited is " + min );
        System.out.println( "Distinct " + distinct );
        System.out.println( "At depth " + ( currentDepth -1 ) + " has node amount " + nodes.size() );

        return nodes.stream().map( node -> node.getCharacter() ).collect( Collectors.toList() );
    }


    private List< TraversedNode > expand( TraversedNode startingPoint )
    {
        List< Character > transitions = startingPoint.getVowelsVisited() >= 2 ? Transitions.getNVTransitions( startingPoint.getCharacter( ) )
                                                                              : Transitions.getTransitions( startingPoint.getCharacter( ) );

        return transitions.stream()
                          .map( ch -> new TraversedNode( ch, startingPoint.getVowelsVisited() + ( Transitions.isVowel( startingPoint.getCharacter() ) ? 1 : 0 ) ) )
                          .collect( Collectors.toList( ) );
    }



}
