// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add('login', (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })
Cypress.Commands.add('GET_StreetLayout', ()=>{
    cy.api({
        method: 'GET',
        url: '/streetlayout',
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('POST_StreetLayout', (route, payload)=>{
    cy.api({
        method: 'POST',
        url: '/streetlayout/'+route,
        body: payload,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('GET_StreetLayout_ById', (id)=>{
    cy.api({
        method: 'GET',
        url: '/streetlayout/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('PUT_StreetLayout', (id, payload)=>{
    cy.api({
        method: 'GET',
        url: '/streetlayout/'+id,
        failOnStatusCode: false,
        body: payload
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('DELETE_StreetLayout', (id)=>{
    cy.api({
        method: 'DELETE',
        url: '/streetlayout/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})


//Product
Cypress.Commands.add('GET_Product', ()=>{
    cy.api({
        method: 'GET',
        url: '/product',
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('POST_Product', (route, payload)=>{
    cy.api({
        method: 'POST',
        url: '/product/'+route,
        body: payload,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('GET_Product_ById', (id)=>{
    cy.api({
        method: 'GET',
        url: '/product/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('PUT_Product', (id, payload)=>{
    cy.api({
        method: 'GET',
        url: '/product/'+id,
        failOnStatusCode: false,
        body: payload
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('DELETE_Product', (id)=>{
    cy.api({
        method: 'DELETE',
        url: '/product/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

//Pallet
Cypress.Commands.add('GET_Pallet', ()=>{
    cy.api({
        method: 'GET',
        url: '/pallet',
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('POST_Pallet', (route, payload)=>{
    cy.api({
        method: 'POST',
        url: '/pallet/'+route,
        body: payload,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('GET_Pallet_ById', (id)=>{
    cy.api({
        method: 'GET',
        url: '/pallet/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('PUT_Pallet', (id, payload)=>{
    cy.api({
        method: 'GET',
        url: '/pallet/'+id,
        failOnStatusCode: false,
        body: payload
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('DELETE_Pallet', (id)=>{
    cy.api({
        method: 'DELETE',
        url: '/pallet/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})


//Packing
Cypress.Commands.add('GET_Packing', ()=>{
    cy.api({
        method: 'GET',
        url: '/packing',
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('POST_Packing', (route, payload)=>{
    cy.api({
        method: 'POST',
        url: '/packing/'+route,
        body: payload,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('GET_Packing_ById', (id)=>{
    cy.api({
        method: 'GET',
        url: '/packing/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('PUT_Packing', (id, payload)=>{
    cy.api({
        method: 'GET',
        url: '/packing/'+id,
        failOnStatusCode: false,
        body: payload
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('DELETE_Packing', (id)=>{
    cy.api({
        method: 'DELETE',
        url: '/packing/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})


//Invoice
Cypress.Commands.add('GET_Invoice', ()=>{
    cy.api({
        method: 'GET',
        url: '/invoice',
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('POST_Invoice', (route, payload)=>{
    cy.api({
        method: 'POST',
        url: '/invoice/'+route,
        body: payload,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('GET_Invoice_ById', (id)=>{
    cy.api({
        method: 'GET',
        url: '/invoice/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('PUT_Invoice', (id, payload)=>{
    cy.api({
        method: 'GET',
        url: '/invoice/'+id,
        failOnStatusCode: false,
        body: payload
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('DELETE_Invoice', (id)=>{
    cy.api({
        method: 'DELETE',
        url: '/invoice/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

//User
Cypress.Commands.add('GET_User', ()=>{
    cy.api({
        method: 'GET',
        url: '/user',
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('POST_User', (route, payload)=>{
    cy.api({
        method: 'POST',
        url: '/user/'+route,
        body: payload,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('GET_User_ById', (id)=>{
    cy.api({
        method: 'GET',
        url: '/user/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('PUT_User', (id, payload)=>{
    cy.api({
        method: 'GET',
        url: '/user/'+id,
        failOnStatusCode: false,
        body: payload
    }).then((response)=>{
        return response
    })
})

Cypress.Commands.add('DELETE_User', (id)=>{
    cy.api({
        method: 'DELETE',
        url: '/user/'+id,
        failOnStatusCode: false
    }).then((response)=>{
        return response
    })
})