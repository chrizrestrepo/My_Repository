package javaOthers.generics;

/**
 * las clases Genericas pueden un numero X de esteriotipos por ejemplo: CLass<K,V,T,N> y de esa misma se pueden implementar
 * N numero de metodos con los Objetos entrantes en el constructor
 * @param <K>
 * @param <T>
 * @param <V>
 * @param <L>
 */
public class GenericClass2<K,T,V,L>{

    private final K objectOne;
    private final T objectTwo;
    private final V objectThree;
    private final L objectFour;

    public GenericClass2(K objectOne, T objectTwo, V objectThree, L objectFour) {
        this.objectOne = objectOne;
        this.objectTwo = objectTwo;
        this.objectThree = objectThree;
        this.objectFour = objectFour;
    }

    public void generateMessageWithGenerics(){
        System.out.println(objectOne.toString()
                .concat(objectTwo.toString())
                .concat(objectThree.toString())
                .concat(objectFour.toString()));
    }
}
