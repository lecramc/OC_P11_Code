import { describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import { Home } from "../pages/Home";
describe("Home page", () => {
  test("Check if home page loaded correctly", async () => {
    render(<Home />);

    expect(await screen.findByText("Rechercher")).toBeInTheDocument();
  });
});
