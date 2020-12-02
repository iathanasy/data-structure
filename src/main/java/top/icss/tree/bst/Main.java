package top.icss.tree.bst;

import top.icss.tree.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author: Mr.Wang
 * @create: 2020-11-30 下午 21:49
 * @description:
 **/
public class Main {

    /**
     * Comparable 的比较 compareTo 方法
     */
    static void test1() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
    }

    /**
     * Comparator 的自定义
     */
    static void test2() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };

        BinarySearchTree<Person> bst1 = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst1.add(new Person(data[i]));
        }

        BinaryTrees.println(bst1);

        BinarySearchTree<Person> bst2 = new BinarySearchTree<>(new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst2.add(new Person(data[i]));
        }
        BinaryTrees.println(bst2);
    }

    /**
     * 随机树打印
     */
    static void test3() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < 40; i++) {
            bst.add((int)(Math.random() * 100));
        }

        String str = BinaryTrees.printString(bst);
        str += "\n";

         BinaryTrees.println(bst);
    }

    static void test4() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 1
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }
        BinaryTrees.println(bst);

        System.out.println("------------------前序遍历------------");
        bst.preorder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
//                return element == 2 ? true : false;
            }
        });
        System.out.println();

        bst.preorderit(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
//                return element == 1 ? true : false;
            }
        });
        System.out.println();
        System.out.println("-------------------------------------");


        System.out.println("------------------中序遍历------------");
        bst.inorder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
//                return element == 4 ? true : false;
            }
        });
        System.out.println();

//        bst.inorderit(new BinarySearchTree.Visitor<Integer>() {
//            public boolean visit(Integer element) {
//                System.out.print(element + " ");
//                return false;
////                return element == 4 ? true : false;
//            }
//        });
//        System.out.println();

        System.out.println("-------------------------------------");


        System.out.println("------------------后序遍历------------");
        bst.postorder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
//                return element == 4 ? true : false;
            }
        });
        System.out.println();
        System.out.println("-------------------------------------");

        System.out.println("------------------层序遍历------------");
        bst.levelorder(new BinarySearchTree.Visitor<Integer>() {
            public boolean visit(Integer element) {
                System.out.print(element + " ");
                return false;
//                return element == 9 ? true : false;
            }
        });
        System.out.println();
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }
}
