package com.base.console.utillibrary;

import com.google.common.collect.*;
import com.google.common.collect.Sets.SetView;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GuavaTest {

    @Test
    public void MultiSet_인터페이스_이용하기() {
        final Multiset<String> strings = HashMultiset.create();
        strings.add("one");
        strings.add("two");
        strings.add("two");
        strings.add("three");
        strings.add("three");
        strings.add("three");

        assertEquals(strings.size(), 6);
        assertEquals(strings.count("two"), 2);

        final Set<String> stringSet = strings.elementSet();

        assertEquals(stringSet.size(), 3);

    }

    @Test
    public void MultiMap_구현을_이용한_주소록의_예(){
        final Multimap<String, String> mapping = HashMultimap.create();
        mapping.put("17번가 거리", "김수원");
        mapping.put("17번가 거리", "김수찬");
        mapping.put("3번가 거리", "박지수");

        final Collection<String> kims = mapping.get("17번가 거리");
        assertEquals(kims.size(), 2);
        assertTrue(kims.contains("김수원"));
        assertTrue(kims.contains("김수찬"));
    }

    /**
     * 양방향 조회 기능을 하는 Bimap
     */
    @Test
    public void BiMap_인터페이스_이용하기() {
        final BiMap<String, String> stockToCompany = HashBiMap.create();
        final BiMap<String, String> companyToStock = stockToCompany.inverse();

        stockToCompany.put("GOOG", "Google");
        stockToCompany.put("APPL", "Apple");

        companyToStock.put("Facebook", "FB");

        assertEquals(stockToCompany.get("GOOG"), "Google");
        assertEquals(companyToStock.get("Apple"), "APPL");
        assertEquals(stockToCompany.get("FB"), "Facebook");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void 수정할_수_없는_컬렉션_생성하기() {
        final List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        final List<Integer> unmodifiableNumbers = Collections.unmodifiableList(numbers);

        unmodifiableNumbers.remove(0);
    }

    @Test
    public void 수정할_수_없는_컬렉션에_속한_값_변경하기() {
        final List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        final List<Integer> unmodifiableNumbers = Collections.unmodifiableList(numbers);

        assertEquals(unmodifiableNumbers.get(0), Integer.valueOf(1));

        numbers.remove(0);

        assertEquals(unmodifiableNumbers.get(0), Integer.valueOf(2));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void 불편성_컬렉션_생성하기() {
        final Set<Integer> numberSet = new HashSet<>();
        numberSet.add(10);
        numberSet.add(20);
        numberSet.add(30);

        final Set<Integer> immutableSet = ImmutableSet.copyOf(numberSet);

        numberSet.remove(10);

        assertTrue(immutableSet.contains(10));

        immutableSet.remove(10);
    }

    /**
     * Iterable 한 객체들 다루기
     */
    @Test
    public void 반복자들을_반복하기() {
        final List<Integer> list1 = new ArrayList<>();

        list1.add(0);
        list1.add(1);
        list1.add(2);

        final List<Integer> list2 = Arrays.asList(3, 4);
        final List<Integer> list3 = new ArrayList<>();
        final List<Integer> list4 = Arrays.asList(5,6,7,8,9);

        final Iterable<Integer> iterable = Iterables.concat(list1, list2, list3,list4);

        final Iterator<Integer> iterator = iterable.iterator();

        for(int i=0; i <=9; i++){
            assertThat(i, is(iterator.next()));
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    public void Guava를_이용한_집합_연산(){
        final Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        final Set<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);

        //합집합
        final Set<Integer> unionSet = Sets.union(set1, set2);
        assertThat(unionSet.size() , is(5));

        //차집합
        final Set<Integer> differenceSet = Sets.difference(set1, set2);
        assertThat(differenceSet.size() , is(2));

        //교집합
        final Set<Integer> intersection = Sets.intersection(set1, set2);
        assertThat(intersection.size() , is(1));

    }
}
