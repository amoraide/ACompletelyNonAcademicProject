package linkedgraph;

import java.util.List;
import java.util.LinkedList;

/**
 * Node class that holds a reference to it's actual value
 *
 * @author ar14rk
 */
public class Node {

	private Node REFERENCE;
	private int ID;
	private int CUMULATIVE_FAKE_LINKS;
	private List<Integer> MERGED_NODES;

	/**
	 * @param id the index of value of the node
	 */
	public Node(int id) {
		this.ID = id;
		this.REFERENCE = this;
		this.CUMULATIVE_FAKE_LINKS = 0;
		this.MERGED_NODES = new LinkedList<>();
	}

	/**
	 * This method will only copy the object's value, not the reference.
	 *
	 * @param other the node object to copy
	 */
	public Node(Node other) {
		this(other.ID);
	}

	public void setLinks(int links) {
		this.CUMULATIVE_FAKE_LINKS = links;
	}

	public int getLinks() {
		return this.CUMULATIVE_FAKE_LINKS;
	}

	public void absorb(int node) {
		this.MERGED_NODES.add(node);
	}

	public void absorb(List<Integer> nodes) {
		this.MERGED_NODES.addAll(nodes);
	}

	public List<Integer> getMergeNodes() {
		return new LinkedList<>(this.MERGED_NODES);
	}

	/**
	 * Sets node reference to itself
	 */
	public void cleanReference() {
		this.setReference(this);
	}

	/**
	 * This method hops through all the references and returns the very latest
	 * reference in the list
	 *
	 * @return
	 */
	public Node getReference() {
		// This method is used primarily to avoid loops
		if (this.REFERENCE == this) {
			return this;
		}
		return this.REFERENCE.getReference();
	}

	/**
	 * This sets this node's reference to be last reference in the list of
	 * references
	 *
	 * @param other
	 */
	public void setReference(Node other) {
		this.REFERENCE = other.getReference();
	}

	/**
	 * Returns the value of the end of the list of references
	 *
	 * @return
	 */
	public int getId() {
		if (this.REFERENCE == this) {
			return this.ID;
		}
		return this.REFERENCE.getId();
	}

	@Override
	public String toString() {
		if (this.REFERENCE == this) {
			return "(" + this.ID + ":" + Integer.toHexString(this.hashCode()) + ")";
		} else {
			return "("
					+ this.ID + ":"
					+ Integer.toHexString(this.hashCode()) + "->"
					+ this.REFERENCE.toString()
					+ ")";
		}
	}
}
