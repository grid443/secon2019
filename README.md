Demo project for [the talk at the SECON'19 conference](https://2019.secon.ru/reports/bezopasnost-v-mikroservisah) about authN/authZ in distributed applications. Application functionality available based on user role:

- Get a list of organizations. Available for all user roles (accountant, auditor, org_auditor)
- Get a list of departments for the specified organization. Available for accountant and auditor
- Read the organization data. Available for users with the authority to read the organization data (accountant, auditor, org_auditor)
- Edit the organization data. Available for users with the authority to edit the organization data (accountant)

User accounts:
- accountant/password
- auditor/password
- org_auditor/password

**mono** - monolith application with all the functionality in a single module and a single database. Used to describe the basic spring security concepts on a simple example.

The distributed version of this application consists of several modules:
- **sso-server** - [CloudFoundry User Account and Authentication (UAA) Server](https://docs.cloudfoundry.org/uaa/uaa-overview.html). This module is responsible for authentication and authorization.
- **client-app** - [Frontend application](https://samnewman.io/patterns/architectural/bff/).
- **organization**
- **department**
- **validation** - This module is used to demonstrate the OAuth2 [client_credentials](https://tools.ietf.org/html/rfc6749#section-4.4) flow. **Department** service calls this service on its own behalf and not on behalf of the user.


Build the project:

`gradlew clean build`

Run SSO-server:

`gradlew -b sso-server/build.gradle uaa`