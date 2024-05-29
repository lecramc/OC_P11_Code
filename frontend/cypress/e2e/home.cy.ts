describe("Hospital Search E2E Test", () => {
  // Ajouter des headers globaux à toutes les requêtes

  beforeEach(() => {
    // Assurez-vous que le serveur est en cours d'exécution
    cy.visit("/"); // Visitez la page d'accueil de l'application
  });

  it("should find the nearest hospital with the specified speciality", () => {
    cy.get(".mapboxgl-ctrl-geocoder--input")
      .type("Bristol")
      .wait(2000)
      .type("{enter}");

    // Sélectionner une spécialité (assurez-vous que l'élément select a un id ou un class)
    cy.get('button[role="combobox"]').click();
    cy.contains('[role="option"]', "Orthodontie").click();

    cy.intercept("GET", "/api/hospitals/search*", (req) => {
      req.headers["X-API-KEY"] =
        "djHB9tuE*CXKnQm33eJA^kLL#db!TU8Dqwf&RueeS7etDNBBgmmTKiRqYJ3CSybggHd4E&b67Vx9";
    }).as("searchRequest");

    // Soumettre le formulaire de recherche
    cy.contains("button", "Rechercher").click();
    cy.wait("@searchRequest");

    // Vérifier que les résultats sont affichés
    cy.get("#nearest-hospital").should("be.visible");
  });
});
