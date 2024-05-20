'use strict'

import {Navbar} from "../../components/Navbar/Navbar";
import {HomepageSection} from "./HomepageSection/HomepageSection";

export function HomePage() {
  return (
      <div className={"homepage"}>
        <Navbar/>
          <HomepageSection/>
      </div>
  );
}