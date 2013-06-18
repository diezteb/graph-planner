SRC = dokumentacja
dokumentacja.pdf: *.tex *.bib
	pdflatex $(SRC)
	bibtex $(SRC)
	pdflatex $(SRC)

.PHONY: clean
clean:
	rm -f *.aux *.lof *.log *.toc *.out *.backup *.tex~ *.bib~ *.bbl *.blg dokumentacja.pdf

