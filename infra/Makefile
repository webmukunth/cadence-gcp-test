.PHONY: install uninstall

install:
	time $(MAKE) -C gcp plan apply
	time $(MAKE) -C helmfile apply

uninstall:
	$(MAKE) -C gcp destroy
