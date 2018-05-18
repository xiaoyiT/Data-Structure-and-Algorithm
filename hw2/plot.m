x = [1024 2048 4096 8192 16384 32768];
y1 = [3061 6133 12277 24565 49141 98293];   %data0
y2 = [46768 169081 660673 2576322 9950984 39442505];    %data1
% plot(x, y1);
% hold on;
% plot(x, y2);
% legend('data0','data1');
% title('Comparison Times Analytics for data with different sizes (shellsort)');
% xlabel('data size');
% ylabel('comparison times');

% y3 = [264541 1027236 4183804 16928767 66641183 267933908];  %Q2
% plot(x,y3);
% title('KendallTau Distance Analytics for data0 and data1');
% xlabel('data size');
% ylabel('KendallTau Distance');


% y3 = [5120 11264 24576 53248 114688 245760];    %data0 for normal mergesort
% y4 = [8954 19934 43944 96074 208695 450132];    %data1 for normal mergesort
% y5 = [5120 11264 24576 53248 114688 245760];      %data0 for bottom-up mergesort
% y6 = [8954 19934 43944 96074 208695 450132];      %data1 for bottom-up mergesort
% plot(x, y3);
% hold on;
% plot(x, y4);
% hold on;
% plot(x, y5);
% hold on;
% plot(x, y6);
% title('Comparison Times Analytics for data with different sizes (MergeSort)');
% legend('data0(top-down)','data1(top-down)','data0(bottom-up)','data1(bottom-up)');
% xlabel('data size');
% ylabel('comparison times');

% y3 = [0.056199253 0.053171133 0.062958733 0.066349748 0.116490186 0.137388397];    %data0 for mergesort
% y4 = [0.047483532 0.056982877 0.061799751 0.076648004 0.122750488 0.167842237];    %data1 for mergesort
y5 = [0.041215384 0.05016515 0.05582772 0.065063961 0.090875409 0.132121892];      %data0 for quicksort
y6 = [0.042566147 0.051457679 0.059239018 0.070251248 0.095896717 0.132780691];      %data1 for quicksort
y7 = [0.039066926 0.048242721 0.054362891 0.064968586 0.091881612 0.130320056];
y8 = [0.042149585 0.049904338 0.056453608 0.067555592 0.092467495 0.129352545];
plot(x, y5);
hold on;
plot(x, y6);
hold on;
plot(x, y7);
hold on;
plot(x, y8);
title('Running Time Analytics for data with different sizes');
legend('data0(standart quicksort)','data1(standard quicksort)','data0(quicksort with cutoff)','data1(quicksort with cutoff)');
xlabel('data size');
ylabel('running time(s)');





