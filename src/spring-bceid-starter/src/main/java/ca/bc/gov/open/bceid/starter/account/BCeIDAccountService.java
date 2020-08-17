package ca.bc.gov.open.bceid.starter.account;

import ca.bc.gov.open.bceid.starter.account.models.IndividualIdentity;

import java.util.Optional;

public interface BCeIDAccountService {

    Optional<IndividualIdentity> getIndividualIdentity(GetAccountRequest request);

}
