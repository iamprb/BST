import java.util.concurrent.atomic.AtomicInteger

    fun insert(root: Node?, key: Int): Node {
        if (root == null) {
            return Node(key)
        }
        if (key < root.data) {
            root.left = insert(root.left, key)
        } else {
            root.right = insert(root.right, key)
        }
        return root
    }

    fun nthLargestNode(root: Node?, k: Int): Int {
        val i = AtomicInteger(0)
        return nthLargestNode(root, i, k)
    }

    fun nthLargestNode(root: Node?, i: AtomicInteger, k: Int): Int {
        if (root == null) {
            return Int.MAX_VALUE
        }
        val left = nthLargestNode(root.right, i, k)
        if (left != Int.MAX_VALUE) {
            return left
        }
        return if (i.incrementAndGet() == k) {
            root.data
        } else nthLargestNode(root.left, i, k)
    }

    fun main() {
        val keys = intArrayOf(10,20,30,40,50)
        var root: Node? = null
        for (key in keys) {
            root = insert(root, key)
        }
        val n = 2
        val result = nthLargestNode(root, n)
        if (result != Int.MAX_VALUE) {
            println(result)
        } else {
            println("Can't process the inpit")
        }
    }