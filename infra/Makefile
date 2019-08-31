.PHONY: install uninstall

install:
	$(MAKE) -C gcp plan apply
	$(MAKE) -C helmfile apply

uninstall:
	$(MAKE) -C gcp destroy
