package com.ah;


//import org.apache.commons.lang3.builder.HashCodeBuilder;
//import org.apache.commons.lang3.tuple.ImmutablePair;
//import org.apache.commons.lang3.tuple.Pair;


/**
 * Created by andrew on 5/17/15.
 */
public class TraversedNode
{
//    private Character ch;
//
//
//    private int vowelsVisited;
//
//
//    private boolean canVisitVowels = true;
//
//
//
//    TraversedNode( Character _ch, int _vowelsVisited )
//    {
//        ch = _ch;
//        vowelsVisited = _vowelsVisited;
//        canVisitVowels = vowelsVisited < 2;
//    }
//
//
//    TraversedNode( Character _ch )
//    {
//        this( _ch, 0 );
//    }
//
//
//    public Character getCharacter()
//    {
//        return ch;
//    }
//
//
//    public int getVowelsVisited()
//    {
//        return vowelsVisited;
//    }
//
//
//    public boolean isVowel()
//    {
//        return Transitions.isVowel( ch );
//    }
//
//
//    public boolean canVisitVowels()
//    {
//        return canVisitVowels;
//    }
//
//
//    public void incrementVisitedCount()
//    {
//        if ( canVisitVowels )
//        {
//            ++vowelsVisited;
//            canVisitVowels = vowelsVisited < 2;
//        }
//    }
//
//
//    public Pair< Character, Integer > getPairing()
//    {
//        return new ImmutablePair<>( ch, vowelsVisited );
//    }
//
//
//    @Override
//    public boolean equals(Object other)
//    {
//        if (other == null)
//        {
//            return false;
//        }
//
//        if (other == this)
//        {
//            return true;
//        }
//
//        if ( !this.getClass( ).equals( other.getClass( ) ) )
//        {
//            return false;
//        }
//
//        TraversedNode otherNode = (TraversedNode) other;
//        return ch.equals( otherNode.ch ) && canVisitVowels == otherNode.canVisitVowels;
//    }
//
//
//    @Override
//    public int hashCode()
//    {
//        int hashCode = new HashCodeBuilder().append( ch ).append( canVisitVowels ).toHashCode();
////        System.out.println( "HC " + ch + " cvv " + canVisitVowels + " hc=" + hashCode );
//        return hashCode;
//    }
//
//
//    @Override
//    public String toString()
//    {
//        return "TV(" + ch + "," + canVisitVowels + ")";
//    }
}
