#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int main(int argc, char* argv[])
{
	FILE** f;
	int i,j;
	char s[2][256];
	int diff;
	int fend;
	
	if (argc<3) {
		printf("Please specify at least 2 input files as command line parameters!\n");
		return 1;
	}
	
	//printf("Allocating file handle array...\n");
	f= (FILE**)malloc(sizeof(FILE*)*(argc-1));
	
	//printf("Opening files...\n");
	for (i=0; i<(argc-1); i++)
		f[i] = fopen(argv[i+1],"r");
	
	//printf("Checking for differences in input files...\n");	
	
	//printf("Reading line...\n");
	diff=0;
	for (j=0; (j<(argc-1)) && (!diff); j++) {
		fscanf(f[j],"%s",s[(j==0)?0:1]);
		if (j>0)
			diff=(strcmp(s[0],s[1])!=0);
	}
	
	while ((!diff) && (!feof(f[0]))) {
		
		//printf("Checking end of file...\n");
		fend=0;
		for (j=1; (j<(argc-1)) && (!fend); j++) {
			fend=(feof(f[j])!=feof(f[0]));
		}
		
		if (!fend) {
			//printf("Reading line...\n");
			diff=0;
			for (j=0; (j<(argc-1)) && (!diff); j++) {
				fscanf(f[j],"%s",s[(j==0)?0:1]);
				if (j>0)
					diff=(strcmp(s[0],s[1])!=0);
			}
		}
	}
	
	//printf("Closing files...\n");
	for (i=0; i<(argc-1); i++)
		fclose(f[i]);
	
	if ((!fend) && (!diff)) {
		printf("Input files match!\n");
		return 0;
	}
	else {
		printf("Input files differ!\n");	
		return 1;
	}		
}
