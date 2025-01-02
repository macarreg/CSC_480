public class KeyGenerator {
    public static String generateKey(String prefix, int i, int j, int k) {
        int[] indices = {i, j, k};
        java.util.Arrays.sort(indices);
        return prefix + "_" + indices[0] + "_" + indices[1] + "_" + indices[2];
    }
}
