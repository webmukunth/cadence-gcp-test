package com.anz.magneto.commons.api.workflow;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LimitType {
  AFPONLY,
  FORCEDEBIT,
  LIMITONLY,
  AFPTHENLIMIT,
  FORCEDEBITLIMIT,
  AFPPLUSLIMIT,
  GROUPLIMIT
}
