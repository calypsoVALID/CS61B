import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int scale; // 网格大小
    private final boolean[] openSites; // 记录每个格子是否打开
    private final WeightedQuickUnionUF uf; // 包含虚拟顶部和底部的UF
    private final WeightedQuickUnionUF ufNoBottom; // 只包含虚拟顶部的UF
    private final int virtualTop; // 虚拟顶部索引
    private final int virtualBottom; // 虚拟底部索引
    private int openCount;

    // 构造函数
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Grid size must be positive");
        }
        this.scale = N;
        int gridSize = N * N;
        openSites = new boolean[gridSize]; // 初始全为False（关闭状态）
        // UF 包含所有格子 + 虚拟顶部 + 虚拟底部
        uf = new WeightedQuickUnionUF(gridSize + 2);

        // UF 包含所有格子 + 虚拟顶部（没有虚拟底部）
        ufNoBottom = new WeightedQuickUnionUF(gridSize + 1);

        virtualTop = gridSize; // 虚拟顶部索引
        virtualBottom = gridSize + 1; // 虚拟底部索引
        openCount = 0;
    }

    public void open(int row, int col) {
        int index = xyTo1D(row, col);
        if (openSites[index]) {
            return; //如果已经打开， 直接返回
        }

        openCount++;
        openSites[index] = true;

        // 如果在第一行连接到虚拟顶部
        if (row == 0) {
            uf.union(index, virtualTop);
            ufNoBottom.union(index, virtualTop);
        }

        // 如果在最后一行连接到虚拟底部
        if (row == scale - 1) {
            uf.union(index, virtualBottom);
        }

        // 检查四个方向的相邻格子（上下左右）
        int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // 检查新位置是否有效
            if (newRow >= 0 && newRow < scale && newCol >= 0 && newCol < scale) {
                int neighbor = xyTo1D(newRow, newCol);
                // 如果相邻格子是打开的，链接他们
                if (openSites[neighbor]) {
                    uf.union(index, neighbor);
                    ufNoBottom.union(index, neighbor);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        return openSites[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        int idx = xyTo1D(row, col);
        // 只有打开的格子才可能满
        // 使用ufNoBottom避免backwash问题
        return openSites[idx] && ufNoBottom.connected(idx, virtualTop);
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        // 检查虚拟顶部和底部是否连接
        return uf.connected(virtualTop, virtualBottom);
    }

    // Helper function
    // 将二维坐标转换为一维索引
    private int xyTo1D(int row, int col) {
        validate(row, col);
        return row * scale + col;
    }

    // 验证行列是否有效
    private void validate(int row, int col) {
        if (row < 0 || row >= scale || col < 0 || col >= scale) {
            throw new IllegalArgumentException("Row or column index out of bounds");
        }
    }
}
