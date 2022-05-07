# NUMA Management Application
**( A CSE 241 Final Programming Project)**




## 3 min Grading Tutorial

#### NUNA Interface

Select [N] 

Select [I] to insert a new property:

 - Give address: 123 Lehigh Ave
 - Give number 123
 - Give name: Super Dupper Nice Apps

 Select [G] to generate some apartments inside of our new property
 - Give the address just make: 123 Lehigh Ave
 - Give the number of apartments you want to make: 5-9 to be fun
 - Give some arbitrary values for next inputs

**All Done in the NUMA menu**: Press [Q] to quit out

#### Property Manager

Select [P] to go to the property interface.

Select [A] To see the apartments you created in the last steps and memorize one apartment number.

Select [T] to add a tenant or two
- Give them some fandom information

Select [L] to go into the Lease Menu

Select [C] to create a new lease
- Give the apartment number and address
- Give other attributes

Select [Q] [G] to quit out of this menu and to see the lease has been created

Select [L] [I] to enter the lease menu and add a tenant to the lease
- Enter the Lease Id that you say when viewing the lease
- Enter how many tenants you want to add
- Put in tenant ID (this can be gathered from the Search People Tool)

Select [Q] [G] to quit out of this menu and to see the tenants have been added to the list

Select [C] to incur some payments!

Select [Q] [Q] To quit to the main menu



#### Tenant Menu

Select [T] to go to the tenant interface
- Enter a TenantId (the assumption is that the tenant should know their id however you can look one up in the Property manager lookup feature)

Select [P] make a payment. 
- You will see the a list of leases that you are on. Select the one you just added
- Enter Date
- Enter Payment method
- Enter Amount

Select [R] to refresh the balance!

**Boom** I hope this enough to grade this properly




 




### [P] Property Manager Interface

Summary: This interface is the most extensive and most use full interface in the application. The primary goal of this interface is to be able to add tenants and perspective tenants, sign leases, record visits, and initiate monthly payments. 

    [T] Add Tenant
        - Add Tenant into the tenant table
    [P] Add Prospective
        - Add Prospective Tenant into per
    [V] Add Visit
        - Document that a prospective tenant has visited an apartment
    [L] Add/Edit Lease
        [C] Create new Lease
            - Add a row to the lease table with a tenant and an apartment
        [I] Add Tenant
            - Put a tenant on the lease
        [q] Quit
            - Quit to the Property Manager 
        [?] Print Help Menu
    [C] Initiate Payments for Lease
        - For a certain address incur the date inputs rents
    Search Tools:
    [K] Search People
        - Search People by last name
    [A] Search Apartments
        - Search apartment by address
    [G] Search Leases
        - Search Leases by address
    [q] Quit
        - Quit to the Main Menu
    [?] Print Help Menu



### [T] Tenant Interface 

Summary: The tenant interface is primarily used to help the tenants pay for their rent

    [P] Make a Payment
        - Add a payment with a leaseId
    [E] Edit Personal Data
        [F] Edit First Name
        [L] Edit Last Name
        [A] Edit Age
        [S] Edit social
        [q] Quit
        [?] Print Help Menu
    [R] Refresh
        - Re-prints name of tenant and amount due
    [q] Quit
    [?] Print Help Menu

### [N] NUMA Manager

Summary: The NUMA interface is used to create properties and apartments in those properties

    [I] Insert Property
        - Add a building to the system
    [G] Generate Apartments
        - Automatically make the number of apartments specified with the set of parameters specified
    [q] Quit
    [?] Print Help Menu