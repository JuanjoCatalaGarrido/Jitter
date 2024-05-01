'use strict'

import {Navbar} from "../../components/Navbar/Navbar";
import {Section} from "../../components/Section/Section";

export function HomePage() {
  return (
      <div className={"homepage"}>
        <Navbar/>
          <Section/>
      </div>
  );
}