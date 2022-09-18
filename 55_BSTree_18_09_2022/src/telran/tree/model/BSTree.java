package telran.tree.model;

import java.util.function.BiConsumer;

public class BSTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public void add(K key, V value) {
	if (root == null) {
	    root = new Node<K, V>(key, value);
	} else {
	    add(root, key, value);
	}
    }

    private void add(Node<K, V> newRoot, K key, V value) {
	if (key.compareTo(newRoot.key) < 0) {
	    if (newRoot.left == null) {
		newRoot.left = new Node<K, V>(key, value);
	    } else {
		add(newRoot.left, key, value);
	    }
	} else {
	    if (newRoot.right == null) {
		newRoot.right = new Node<K, V>(key, value);
	    } else {
		add(newRoot.right, key, value);
	    }
	}

    }

    public boolean contains(K key) {
	return search(root, key) != null;
    }

    public V get(K key) {
	Node<K, V> node = search(root, key);
	return node == null ? null : node.value;
    }

    private Node<K, V> search(Node<K, V> newRoot, K key) {
	if (newRoot == null || newRoot.key.compareTo(key) == 0) {
	    return newRoot;
	}
	if (newRoot.key.compareTo(key) < 0) {
	    return search(newRoot.right, key);
	} else {
	    return search(newRoot.left, key);
	}

    }

    public void traverse(BiConsumer<K, V> biConsumer) {
	traverseInFix(root, biConsumer);
    }

    private void traverseInFix(Node<K, V> newRoot, BiConsumer<K, V> biConsumer) {
	if (newRoot == null) {
	    return;
	}
	traverseInFix(newRoot.left, biConsumer);
	biConsumer.accept(newRoot.key, newRoot.value);
	traverseInFix(newRoot.right, biConsumer);
    }

    private static class Node<K, V> {
	K key;
	V value;
	Node<K, V> left;
	Node<K, V> right;

	public Node(K key, V value) {
	    this.key = key;
	    this.value = value;
	}

    }
}
