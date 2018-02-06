x1 = [8 32 128 512 1024 4096 4192 8192];
% y1: naive 3-sum
y1 = [0.02796041 0.0294864 0.03210467 0.051331606 0.128754654 3.213849583 3.378107722 24.019857894];
% y2: sophisticated 3-sum
y2 = [0.029684865 0.029326568 0.032546547 0.045990868 0.061820437 0.339654079 0.289655603 0.801861948];
% plot(x, y1);
% hold on;
% plot(x, y2);
% legend('naive 3-sum','sophisticatd 3-sum');
% title('Running time Analytics for 3 sum Algorithms');
% xlabel('int');
% ylabel('running time(ms)');

x2 = [8 32 128 512 1024 4096 8192];
% y3: quickfind uf
y3 = [0.00113992 0.002118752 0.005213653 0.008137138 0.010841518 0.02161758 0.033442334];
% y4: quick union uf
y4 = [2 4 8 21 34 69 96];
% y5: balancedQU
y5 = [2 3 8 21 33 67 93];
plot(x2, y3);
hold on;
plot(x2, y4);
hold on;
plot(x2, y5);
legend('QuickFind', 'QuickUnion', 'Weighted QuickUnion');
title('Running time Analytics for Union Find Algorithms');
xlabel('pairs');
ylabel('running time(s)');