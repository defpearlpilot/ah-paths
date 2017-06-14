package com.ah;


//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.google.common.collect.Sets;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;


/**
 * This captures the transitions from a particular node to nodes on the path
 */
public class Transitions
{
//    private static final Set<Character> VOWELS = Sets.newHashSet( 'A', 'E', 'I', 'O', 'U' );
//
//
//    private static final Map< Character, List< Character > > STATE_TRANSITIONS = Maps.newHashMap( );
//
//
//    private static final Map< Character, List< Character > > NONVOWEL_TRANSITIONS = Maps.newHashMap( );
//
//
//    static
//    {
//
//        STATE_TRANSITIONS.put( 'A', Lists.newArrayList( 'L', 'H' ) );
//        STATE_TRANSITIONS.put( 'E', Lists.newArrayList( 'H', 'N' ) );
//        STATE_TRANSITIONS.put( 'I', Lists.newArrayList( '2', 'L', 'B' ) );
//        STATE_TRANSITIONS.put( 'O', Lists.newArrayList( '2', 'H', 'D' ) );
//
//        STATE_TRANSITIONS.put( 'B', Lists.newArrayList( 'K', 'M', 'I' ) );
//        STATE_TRANSITIONS.put( 'C', Lists.newArrayList( 'F', 'L', 'N', 'J' ) );
//        STATE_TRANSITIONS.put( 'D', Lists.newArrayList( 'G', 'M', 'O' ) );
//        STATE_TRANSITIONS.put( 'F', Lists.newArrayList( 'C', 'M', 'I' ) );
//        STATE_TRANSITIONS.put( 'G', Lists.newArrayList( 'D', 'N', '2' ) );
//        STATE_TRANSITIONS.put( 'H', Lists.newArrayList( 'A', 'K', '1', '3', 'O', 'E' ) );
//        STATE_TRANSITIONS.put( 'J', Lists.newArrayList( '3', 'M', 'C' ) );
//        STATE_TRANSITIONS.put( 'K', Lists.newArrayList( 'B', 'H', '2' ) );
//        STATE_TRANSITIONS.put( 'L', Lists.newArrayList( 'A', 'C', 'I', '3' ) );
//        STATE_TRANSITIONS.put( 'M', Lists.newArrayList( 'F', 'B', 'D', 'J' ) );
//        STATE_TRANSITIONS.put( 'N', Lists.newArrayList( '1', 'G', 'C', 'E' ) );
//        STATE_TRANSITIONS.put( '1', Lists.newArrayList( 'F', 'H', 'N' ) );
//        STATE_TRANSITIONS.put( '2', Lists.newArrayList( 'K', 'G', 'I', 'O' ) );
//        STATE_TRANSITIONS.put( '3', Lists.newArrayList( 'L', 'H', 'J' ) );
//
//
//        STATE_TRANSITIONS.entrySet().forEach( entry ->
//            NONVOWEL_TRANSITIONS.put( entry.getKey( ),
//                                      entry.getValue().stream( )
//                                                      .filter( ch -> !VOWELS.contains( ch ) )
//                                                      .collect( Collectors.toList( ) ) )
//        );
//    }
//
//
//    public static boolean isValidNode( Character node )
//    {
//        return STATE_TRANSITIONS.keySet().contains( node );
//    }
//
//
//    public static Set<Character> getValidNodes()
//    {
//        return STATE_TRANSITIONS.keySet();
//    }
//
//
//    public static boolean isVowel( Character node )
//    {
//        return VOWELS.contains( node );
//    }
//
//    public static List< Character > getTransitions( Character node )
//    {
//        return STATE_TRANSITIONS.get( node );
//    }
//
//
//    public static List< Character > getNVTransitions( Character node )
//    {
//        return NONVOWEL_TRANSITIONS.get( node );
//    }
}
