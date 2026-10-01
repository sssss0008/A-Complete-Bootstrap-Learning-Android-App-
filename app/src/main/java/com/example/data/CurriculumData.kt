package com.example.data

import com.example.model.*

object CurriculumData {

    val levels: List<CurriculumLevel> = listOf(
        CurriculumLevel(
            levelNumber = 1,
            title = "Introduction to Bootstrap 5",
            category = "Foundation",
            description = "What Bootstrap is, mobile-first responsive web design, CDN quickstart, and viewport setup.",
            lessons = listOf(
                Lesson(
                    id = "bs_1_1",
                    levelNumber = 1,
                    title = "What is Bootstrap 5?",
                    subtitle = "The world's most popular frontend CSS framework",
                    durationMin = 8,
                    summary = "Learn why Bootstrap powers millions of production websites with pre-styled components and responsive utilities.",
                    conceptExplanation = "Bootstrap is a powerful, feature-packed frontend toolkit. Rather than writing thousands of lines of custom CSS from scratch for every project, Bootstrap gives you a tested 12-column responsive grid system, pre-built components (buttons, modals, navbars, cards), and powerful utility classes. In Bootstrap 5, jQuery was completely removed in favor of pure vanilla JavaScript, and CSS custom properties (variables) were added for modern theming.",
                    codeSnippet = """<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <!-- Crucial for responsive mobile rendering: -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Learn Bootstrap by Awiskar Acharya</title>
    <!-- Bootstrap 5 CSS via CDN: -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
    <div class="container py-5 text-center">
      <h1 class="text-primary fw-bold">Hello, Bootstrap!</h1>
      <p class="lead text-secondary">Learn. Build. Style. Master Bootstrap.</p>
      <button class="btn btn-primary btn-lg">Get Started</button>
    </div>
  </body>
</html>""",
                    visualType = VisualType.UTILITY_CLASSES,
                    interactiveTask = "Inspect the mobile-first viewport meta tag and CDN link.",
                    quizQuestion = "Why is <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> mandatory in Bootstrap?",
                    quizOptions = listOf(
                        "It connects the browser to Google Fonts",
                        "It ensures proper touch zooming and responsive scaling across mobile devices",
                        "It compiles JavaScript into WebAssembly",
                        "It forces dark mode on OLED screens"
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = "Bootstrap is mobile-first; the viewport meta tag instructs mobile browsers to render at the device's native screen width rather than zooming out to a desktop canvas."
                )
            )
        ),
        CurriculumLevel(
            levelNumber = 2,
            title = "Containers & Layout",
            category = "Grid",
            description = "The foundation of all Bootstrap layouts: .container, .container-fluid, and responsive containers.",
            lessons = listOf(
                Lesson(
                    id = "bs_2_1",
                    levelNumber = 2,
                    title = "Containers: Fixed vs Fluid",
                    subtitle = "Padding and centering your content across screen sizes",
                    durationMin = 10,
                    summary = "Containers are the most basic layout element in Bootstrap and are required when using the grid system.",
                    conceptExplanation = "A .container provides a responsive fixed-width container: its max-width changes at each breakpoint (540px at sm, 720px at md, 960px at lg, etc.) and it automatically centers itself with horizontal margin auto. A .container-fluid spans 100% of the viewport width across all breakpoints. You can also use responsive containers like .container-lg to stay 100% wide until the large breakpoint.",
                    codeSnippet = """<!-- Fixed centered container (has max-width at breakpoints): -->
<div class="container bg-light p-4 rounded shadow-sm">
  <h2>Fixed Container</h2>
  <p>Centered with automatic margins and responsive max-widths.</p>
</div>

<!-- 100% Full-width fluid container: -->
<div class="container-fluid bg-dark text-white p-4 my-3">
  <h2>Fluid Container</h2>
  <p>Always spans 100% width edge-to-edge on all screen sizes.</p>
</div>""",
                    visualType = VisualType.GRID_SYSTEM,
                    interactiveTask = "Compare container vs container-fluid behavior.",
                    quizQuestion = "What is the key difference between .container and .container-fluid?",
                    quizOptions = listOf(
                        ".container is only for images",
                        ".container has breakpoint-specific max-widths and centers, while .container-fluid is always 100% width",
                        ".container-fluid requires jQuery",
                        ".container disables all margins"
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = ".container has predefined max-widths (e.g. 1140px on xl), whereas .container-fluid always stretches across the entire screen."
                )
            )
        ),
        CurriculumLevel(
            levelNumber = 3,
            title = "The 12-Column Grid System",
            category = "Grid",
            description = "Rows, columns, auto-layout, column wrapping, and the 12-column mathematical foundation.",
            lessons = listOf(
                Lesson(
                    id = "bs_3_1",
                    levelNumber = 3,
                    title = "Understanding the 12-Column Grid",
                    subtitle = "How rows and cols combine to build flexible multi-column layouts",
                    durationMin = 12,
                    summary = "Bootstrap's grid system uses flexbox to divide any row into 12 proportional columns.",
                    conceptExplanation = "Bootstrap's grid is built with flexbox. Columns must always be direct children of a .row, and rows must be inside a .container. The row uses negative margins to counteract the column gutters. You can specify widths from 1 to 12 (e.g., col-6 takes half the width). If columns exceed 12 in a single row, they automatically wrap to a new line.",
                    codeSnippet = """<div class="container">
  <!-- Equal width auto columns (3 cols = 33.3% each): -->
  <div class="row">
    <div class="col bg-primary text-white p-3">Column 1</div>
    <div class="col bg-success text-white p-3">Column 2</div>
    <div class="col bg-info text-white p-3">Column 3</div>
  </div>

  <!-- Explicit column numbers (total 12): -->
  <div class="row mt-3">
    <div class="col-8 bg-dark text-white p-3">Main Content (8 cols / 66.6%)</div>
    <div class="col-4 bg-secondary text-white p-3">Sidebar (4 cols / 33.3%)</div>
  </div>
</div>""",
                    visualType = VisualType.GRID_SYSTEM,
                    interactiveTask = "Test different column widths adding up to 12.",
                    quizQuestion = "In Bootstrap, what must columns (.col-*) be immediate children of?",
                    quizOptions = listOf(
                        "A <p> paragraph tag",
                        "A .row container",
                        "A .table element",
                        "A <span> tag"
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = "Columns must always be placed directly inside a .row element so negative margins align the gutters correctly."
                )
            )
        ),
        CurriculumLevel(
            levelNumber = 4,
            title = "Responsive Breakpoints",
            category = "Grid",
            description = "Mobile-first breakpoint tiers: xs, sm (≥576px), md (≥768px), lg (≥992px), xl (≥1200px), xxl (≥1400px).",
            lessons = listOf(
                Lesson(
                    id = "bs_4_1",
                    levelNumber = 4,
                    title = "Mobile-First Responsive Column Classes",
                    subtitle = "Stacking on smartphones, side-by-side on tablets and laptops",
                    durationMin = 15,
                    summary = "Combine classes like col-12 col-md-6 col-lg-4 to transform layouts across screen sizes.",
                    conceptExplanation = "Because Bootstrap is mobile-first, a class like .col-12 applies to all screens from mobile up. When you add .col-md-6, screens 768px and wider switch to 2 columns. When you add .col-lg-4, screens 992px and wider switch to 3 columns per row. This is the superpower of Bootstrap's responsive architecture.",
                    codeSnippet = """<div class="container">
  <div class="row g-3">
    <!-- 1 column on mobile (col-12)
         2 columns on tablet (col-md-6)
         3 columns on desktop (col-lg-4) -->
    <div class="col-12 col-md-6 col-lg-4">
      <div class="card p-3 shadow-sm">Card 1</div>
    </div>
    <div class="col-12 col-md-6 col-lg-4">
      <div class="card p-3 shadow-sm">Card 2</div>
    </div>
    <div class="col-12 col-md-6 col-lg-4">
      <div class="card p-3 shadow-sm">Card 3</div>
    </div>
  </div>
</div>""",
                    visualType = VisualType.BREAKPOINTS_FLOW,
                    interactiveTask = "Toggle between mobile, tablet, and desktop breakpoints.",
                    quizQuestion = "If a column has class=\"col-12 col-md-6\", what width will it have on an iPhone screen (390px)?",
                    quizOptions = listOf(
                        "50% width",
                        "100% full width (stacked vertically)",
                        "33% width",
                        "0% width (hidden)"
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = "On screens under 768px (like phones), .col-12 takes effect, spanning the full 100% width."
                )
            )
        ),
        CurriculumLevel(
            levelNumber = 5,
            title = "Flexbox Utilities",
            category = "Utilities",
            description = "Alignment, justification, flex-direction, wrapping, and building custom layouts without writing CSS.",
            lessons = listOf(
                Lesson(
                    id = "bs_5_1",
                    levelNumber = 5,
                    title = "Mastering Bootstrap Flexbox Classes",
                    subtitle = "d-flex, justify-content-between, align-items-center and more",
                    durationMin = 14,
                    summary = "Quickly manage layout, alignment, and sizing of grid columns, navigation, and custom components.",
                    conceptExplanation = "Bootstrap provides dozens of flexbox utility classes: Apply .d-flex to turn any element into a flex container. Use .justify-content-between or .justify-content-center to align along the main axis. Use .align-items-center to vertically center items. Use .flex-column to stack items vertically.",
                    codeSnippet = """<!-- Vertically & Horizontally centered hero box: -->
<div class="d-flex justify-content-center align-items-center bg-light" style="height: 160px;">
  <span class="fs-4 fw-bold text-primary">Centered Perfectly!</span>
</div>

<!-- Navigation header with brand on left, actions on right: -->
<div class="d-flex justify-content-between align-items-center p-3 bg-dark text-white rounded">
  <span class="fw-bold">MyBrand</span>
  <div class="d-flex gap-2">
    <button class="btn btn-outline-light btn-sm">Log in</button>
    <button class="btn btn-primary btn-sm">Sign up</button>
  </div>
</div>""",
                    visualType = VisualType.UTILITY_CLASSES,
                    interactiveTask = "Inspect justify-content and align-items alignments.",
                    quizQuestion = "Which Bootstrap class vertically centers child items in a flex container?",
                    quizOptions = listOf(
                        "valign-center",
                        "align-items-center",
                        "justify-content-center",
                        "vertical-center"
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = "align-items-center controls cross-axis alignment, which centers items vertically in a default horizontal flex row."
                )
            )
        ),
        CurriculumLevel(
            levelNumber = 6,
            title = "Spacing Utilities: Margin & Padding",
            category = "Utilities",
            description = "The spacing scale (0 to 5, auto), m-* and p-* directional abbreviations, and responsive spacing.",
            lessons = listOf(
                Lesson(
                    id = "bs_6_1",
                    levelNumber = 6,
                    title = "The Bootstrap Spacing Scale",
                    subtitle = "m-3, p-4, mx-auto, my-5, and gap utilities",
                    durationMin = 10,
                    summary = "Assign responsive margin or padding values with simple shorthand classes.",
                    conceptExplanation = "Format: {property}{sides}-{size}. Property: m for margin, p for padding. Sides: t (top), b (bottom), s (start/left in LTR), e (end/right in LTR), x (horizontal), y (vertical), blank (all 4 sides). Size ranges from 0 ($0) to 5 ($3rem = 48px). mx-auto centers block elements horizontally.",
                    codeSnippet = """<!-- Padding 4 on all sides, margin bottom 3: -->
<div class="p-4 mb-3 bg-white border rounded">
  <h4>Card with p-4 mb-3</h4>
  <!-- Margin top 2: -->
  <p class="mt-2 text-muted">Clean spacing without custom CSS.</p>
</div>

<!-- Centered button with horizontal auto margin: -->
<div class="d-flex">
  <button class="btn btn-primary mx-auto">Centered Button (mx-auto)</button>
</div>""",
                    visualType = VisualType.UTILITY_CLASSES,
                    interactiveTask = "Experiment with margin and padding scale sizes.",
                    quizQuestion = "What does the class py-3 apply to an element?",
                    quizOptions = listOf(
                        "Padding left and right of size 3",
                        "Padding top and bottom (vertical padding) of size 3",
                        "Margin on all 4 sides",
                        "Yellow background color"
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = "'p' stands for padding, and 'y' specifies the vertical axis (both top and bottom)."
                )
            )
        ),
        CurriculumLevel(
            levelNumber = 7,
            title = "Bootstrap Cards",
            category = "Components",
            description = "Building flexible content containers with card-header, card-body, card-footer, and images.",
            lessons = listOf(
                Lesson(
                    id = "bs_7_1",
                    levelNumber = 7,
                    title = "Card Components in Depth",
                    subtitle = "The versatile container for articles, products, and user profiles",
                    durationMin = 12,
                    summary = "Cards replace legacy panels, wells, and thumbnails in Bootstrap 5.",
                    conceptExplanation = "A .card provides a bordered surface with default rounded corners and padding. Structure: .card wraps the container; .card-header contains title tabs or icons; .card-body contains the main content; .card-title and .card-text provide standardized typography; .card-footer sits at the bottom.",
                    codeSnippet = """<div class="card shadow-sm" style="max-width: 320px;">
  <div class="card-header bg-primary text-white fw-bold">
    Featured Course
  </div>
  <div class="card-body">
    <h5 class="card-title">Bootstrap 5 Masterclass</h5>
    <p class="card-text text-muted">
      Master responsive web development with Awiskar Acharya.
    </p>
    <a href="#" class="btn btn-primary btn-sm">Start Learning</a>
  </div>
  <div class="card-footer text-muted small">
    Updated 2 days ago
  </div>
</div>""",
                    visualType = VisualType.COMPONENT_LAB,
                    interactiveTask = "Inspect card header, body, and footer structure.",
                    quizQuestion = "What Bootstrap class provides the primary padding inside a card?",
                    quizOptions = listOf(
                        ".card-inner",
                        ".card-body",
                        ".card-container",
                        ".card-padding"
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = ".card-body applies standard padding (1rem) to the card's main content area."
                )
            )
        ),
        CurriculumLevel(
            levelNumber = 8,
            title = "Responsive Navbar & Menus",
            category = "Components",
            description = "Building modern responsive headers with branding, navigation links, and mobile hamburger togglers.",
            lessons = listOf(
                Lesson(
                    id = "bs_8_1",
                    levelNumber = 8,
                    title = "Building a Responsive Navigation Bar",
                    subtitle = "Collapsing into a mobile hamburger menu seamlessly",
                    durationMin = 15,
                    summary = "The Bootstrap Navbar automatically collapses into a hamburger drawer on smaller screens.",
                    conceptExplanation = "A navbar uses .navbar, .navbar-expand-lg (specifying when the menu expands), and color themes like .navbar-dark .bg-dark. The .navbar-toggler button triggers the .collapse .navbar-collapse container using data-bs-toggle=\"collapse\" and data-bs-target=\"#navContent\".",
                    codeSnippet = """<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3">
  <a class="navbar-brand fw-bold text-info" href="#">LearnBootstrap</a>
  
  <!-- Mobile Hamburger Toggler Button: -->
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navMenu">
    <span class="navbar-toggler-icon"></span>
  </button>

  <!-- Collapsible links: -->
  <div class="collapse navbar-collapse" id="navMenu">
    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
      <li class="nav-item"><a class="nav-link active" href="#">Home</a></li>
      <li class="nav-item"><a class="nav-link" href="#">Grid</a></li>
      <li class="nav-item"><a class="nav-link" href="#">Components</a></li>
    </ul>
    <button class="btn btn-outline-info btn-sm">Sign In</button>
  </div>
</nav>""",
                    visualType = VisualType.RESPONSIVE_NAVBAR,
                    interactiveTask = "Simulate hamburger toggling on mobile vs desktop.",
                    quizQuestion = "What does the class navbar-expand-lg mean?",
                    quizOptions = listOf(
                        "The navbar is always collapsed into a hamburger menu on all devices",
                        "The navbar expands horizontally on large screens (≥992px) and collapses into a hamburger on smaller screens",
                        "The navbar has extra large font size",
                        "The navbar is fixed to the bottom of the screen"
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = "navbar-expand-lg specifies the breakpoint threshold where the mobile hamburger menu unfolds into full horizontal links."
                )
            )
        ),
        CurriculumLevel(
            levelNumber = 9,
            title = "Bootstrap Modals & Dialogs",
            category = "Components",
            description = "Interactive dialog overlays, alert confirmations, backdrops, and modal sizing.",
            lessons = listOf(
                Lesson(
                    id = "bs_9_1",
                    levelNumber = 9,
                    title = "Modals and Popups",
                    subtitle = "Triggering lightboxes and dialogs with pure HTML attributes",
                    durationMin = 12,
                    summary = "Bootstrap modals add dialogs for user notifications, forms, and custom content.",
                    conceptExplanation = "Modals are positioned fixed over the viewport with an animated backdrop. You can trigger a modal without writing a single line of JavaScript by adding data-bs-toggle=\"modal\" and data-bs-target=\"#myModal\" to any button. Inside, .modal-dialog controls width (modal-sm, modal-lg, modal-xl) and centering (.modal-dialog-centered).",
                    codeSnippet = """<!-- Trigger button: -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  Launch Modal
</button>

<!-- Modal Container: -->
<div class="modal fade" id="exampleModal" tabindex="-1">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Modal Title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <p>Bootstrap 5 modal dialog content goes here.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>""",
                    visualType = VisualType.COMPONENT_LAB,
                    interactiveTask = "Test modal launch, centering, and backdrop dismissal.",
                    quizQuestion = "Which HTML attribute tells Bootstrap to close a modal when a button is clicked?",
                    quizOptions = listOf(
                        "data-bs-close=\"true\"",
                        "data-bs-dismiss=\"modal\"",
                        "onclick=\"close()\"",
                        "data-bs-hide=\"dialog\""
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = "data-bs-dismiss=\"modal\" automatically connects Bootstrap's JavaScript to hide the modal and remove the backdrop."
                )
            )
        ),
        CurriculumLevel(
            levelNumber = 10,
            title = "Forms & Validation",
            category = "Components",
            description = "Form controls, floating labels, input groups, checkboxes, radios, and validation states.",
            lessons = listOf(
                Lesson(
                    id = "bs_10_1",
                    levelNumber = 10,
                    title = "Form Controls & Floating Labels",
                    subtitle = "Building accessible, clean input forms with instant feedback",
                    durationMin = 14,
                    summary = "Bootstrap styles standard HTML form inputs into modern, consistent form controls.",
                    conceptExplanation = "Use .form-control for text inputs and textareas, .form-select for dropdowns, and .form-check for checkboxes/radios. Floating labels (.form-floating) create modern animated input labels that float above the field when focused or populated.",
                    codeSnippet = """<!-- Floating label input: -->
<div class="form-floating mb-3">
  <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
  <label for="floatingInput">Email address</label>
</div>

<!-- Input group with prepended symbol: -->
<div class="input-group mb-3">
  <span class="input-group-text">@</span>
  <input type="text" class="form-control" placeholder="Username">
</div>""",
                    visualType = VisualType.FORM_VALIDATION,
                    interactiveTask = "Test floating label animations and input groups.",
                    quizQuestion = "Which class is applied to a standard <input type=\"text\"> in Bootstrap?",
                    quizOptions = listOf(
                        ".input-field",
                        ".form-control",
                        ".text-input",
                        ".form-input"
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = ".form-control is Bootstrap's universal styling class for text inputs, file pickers, and textareas."
                )
            )
        ),
        CurriculumLevel(
            levelNumber = 11,
            title = "React-Bootstrap Integration",
            category = "React-Bootstrap",
            description = "Using Bootstrap components inside modern React applications: <Container>, <Row>, <Col>, <Button>, <Modal>.",
            lessons = listOf(
                Lesson(
                    id = "bs_11_1",
                    levelNumber = 11,
                    title = "React-Bootstrap: Components without jQuery",
                    subtitle = "Replacing HTML classes with type-safe React components",
                    durationMin = 15,
                    summary = "Learn how React-Bootstrap reimplements Bootstrap JavaScript components as pure React components.",
                    conceptExplanation = "React-Bootstrap replaces data-bs-* HTML attributes with true React state and props. Instead of <div class=\"container\">, you write <Container>. Instead of <button class=\"btn btn-primary\">, you write <Button variant=\"primary\">. Modals use the 'show' prop controlled by React useState.",
                    codeSnippet = """import React, { useState } from 'react';
import { Container, Row, Col, Card, Button, Modal } from 'react-bootstrap';

export default function App() {
  const [showModal, setShowModal] = useState(false);

  return (
    <Container className="py-4">
      <Row className="g-3">
        <Col xs={12} md={6}>
          <Card className="shadow-sm">
            <Card.Body>
              <Card.Title>React + Bootstrap</Card.Title>
              <Card.Text>
                Type-safe components with iconic Bootstrap styling.
              </Card.Text>
              <Button variant="primary" onClick={() => setShowModal(true)}>
                Open Modal
              </Button>
            </Card.Body>
          </Card>
        </Col>
      </Row>

      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Modal Heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>Pure React state controlling Bootstrap modal!</Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>Close</Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}""",
                    visualType = VisualType.REACT_BOOTSTRAP_FLOW,
                    interactiveTask = "Observe React props mapping directly into Bootstrap components.",
                    quizQuestion = "In React-Bootstrap, how is modal visibility controlled?",
                    quizOptions = listOf(
                        "Using document.getElementById('modal').show()",
                        "Via the 'show={boolean}' prop bound to React state",
                        "By reloading the web page",
                        "Using jQuery animations"
                    ),
                    correctQuizIndex = 1,
                    quizExplanation = "React-Bootstrap embraces the React declarative model, controlling modal visibility via the boolean 'show' prop."
                )
            )
        )
    )

    // Bootstrap Utilities Catalog
    val utilitiesList: List<BootstrapUtilityItem> = listOf(
        BootstrapUtilityItem(
            id = "util_spacing",
            name = "Spacing (Margin & Padding)",
            category = "Spacing",
            syntax = "{property}{sides}-{size} (e.g. m-3, p-4, mx-auto)",
            summary = "Control margins and padding quickly with proportional spacing tokens.",
            explanation = "0 = 0px, 1 = 0.25rem (4px), 2 = 0.5rem (8px), 3 = 1rem (16px), 4 = 1.5rem (24px), 5 = 3rem (48px), auto = auto margin.",
            codeExample = """<div class="p-3 mb-2 bg-primary text-white">.p-3 .mb-2</div>
<div class="mx-auto" style="width: 200px;">Centered with .mx-auto</div>""",
            commonMistakes = listOf(
                "Using custom inline styles style=\"margin: 15px\" instead of standard Bootstrap spacing classes",
                "Forgetting that mx-auto requires a defined width or display block to center horizontally"
            ),
            realWorldCase = "Card content breathing room, page section separators, button spacing."
        ),
        BootstrapUtilityItem(
            id = "util_flex",
            name = "Flexbox Alignment",
            category = "Flexbox",
            syntax = "d-flex justify-content-{alignment} align-items-{alignment}",
            summary = "Align, justify, and distribute child elements along flex axes.",
            explanation = "Apply d-flex to turn any element into a flexbox container. Use justify-content-start/end/center/between/around and align-items-start/center/end/stretch.",
            codeExample = """<div class="d-flex justify-content-between align-items-center p-3 bg-light">
  <span>Left Logo</span>
  <span>Right Menu</span>
</div>""",
            commonMistakes = listOf(
                "Using text-center to center child flex items (use justify-content-center instead)",
                "Forgetting that align-items works on the cross axis, which changes if flex-column is used"
            ),
            realWorldCase = "Sticky headers, icon + text rows, centered loading spinners, responsive card actions."
        ),
        BootstrapUtilityItem(
            id = "util_colors",
            name = "Colors & Backgrounds",
            category = "Colors",
            syntax = "text-{theme} bg-{theme} (e.g. text-primary, bg-dark)",
            summary = "Semantic color classes for text, backgrounds, and borders.",
            explanation = "Themes include: primary (blue), secondary (gray), success (green), danger (red), warning (yellow), info (cyan), light, dark, and body.",
            codeExample = """<p class="text-primary">Primary blue text</p>
<p class="text-success">Success green text</p>
<div class="p-3 bg-dark text-white rounded">Dark container with light text</div>""",
            commonMistakes = listOf(
                "Using text-dark on a dark background resulting in unreadable low-contrast text",
                "Not pairing bg-dark with text-white"
            ),
            realWorldCase = "Status badges, alert banners, call-to-action buttons, dark footer panels."
        )
    )

    // Practice challenges
    val challenges: List<CodingChallenge> = listOf(
        CodingChallenge(
            id = "ch_bs_1",
            title = "Build a 3-Column Responsive Pricing Table",
            category = "Grid & Cards",
            difficulty = "Beginner",
            problem = "Create a responsive pricing grid using Bootstrap 5. On phones, cards must stack (100% width). On tablets and laptops, cards must form 3 equal columns side-by-side with padding and shadow.",
            requirements = listOf(
                "Use a .container and .row with gap (g-3 or g-4)",
                "Use .col-12 .col-md-4 for responsive breakpoint scaling",
                "Wrap each pricing tier in a .card with .card-body and .shadow-sm",
                "Include a .btn .btn-primary for the plan call to action"
            ),
            starterCode = """<div class="container">
  <!-- TODO: Add row and responsive columns here -->
  <div>
    <h3>Basic Plan</h3>
    <p>$9/mo</p>
    <button>Choose Plan</button>
  </div>
  <div>
    <h3>Pro Plan</h3>
    <p>$29/mo</p>
    <button>Choose Plan</button>
  </div>
  <div>
    <h3>Enterprise</h3>
    <p>$99/mo</p>
    <button>Choose Plan</button>
  </div>
</div>""",
            solutionCode = """<div class="container py-4">
  <div class="row g-4">
    <div class="col-12 col-md-4">
      <div class="card h-100 shadow-sm text-center p-3">
        <div class="card-body">
          <h4 class="card-title">Basic Plan</h4>
          <h2 class="text-primary my-3">$9<small class="text-muted">/mo</small></h2>
          <p class="card-text text-muted">Essential features for beginners.</p>
          <button class="btn btn-outline-primary w-100">Choose Basic</button>
        </div>
      </div>
    </div>
    <div class="col-12 col-md-4">
      <div class="card h-100 shadow text-center p-3 border-primary">
        <div class="card-body">
          <span class="badge bg-primary mb-2">MOST POPULAR</span>
          <h4 class="card-title">Pro Plan</h4>
          <h2 class="text-primary my-3">$29<small class="text-muted">/mo</small></h2>
          <p class="card-text text-muted">Advanced tools for growing teams.</p>
          <button class="btn btn-primary w-100">Choose Pro</button>
        </div>
      </div>
    </div>
    <div class="col-12 col-md-4">
      <div class="card h-100 shadow-sm text-center p-3">
        <div class="card-body">
          <h4 class="card-title">Enterprise</h4>
          <h2 class="text-primary my-3">$99<small class="text-muted">/mo</small></h2>
          <p class="card-text text-muted">Dedicated support and custom SLA.</p>
          <button class="btn btn-outline-primary w-100">Contact Us</button>
        </div>
      </div>
    </div>
  </div>
</div>""",
            testExpectations = listOf(
                "Contains .row element with grid gutter",
                "Uses .col-12 and .col-md-4 for responsive breakpoints",
                "Includes .card with .shadow and .card-body",
                "Adds full width call-to-action buttons (.w-100)"
            ),
            hint = "Use class=\"col-12 col-md-4\" on each column, and use .h-100 on the cards so they match height.",
            solutionExplanation = "By using .col-12 on mobile, each pricing card takes 100% width. On screens 768px and wider (md), .col-md-4 assigns 4 columns (12 / 4 = 3 equal columns)."
        ),
        CodingChallenge(
            id = "ch_bs_2",
            title = "Fix the Overflowing Navbar Bug",
            category = "Navigation",
            difficulty = "Intermediate",
            problem = "A developer created a navbar, but on mobile devices the menu items overflow and break out of the screen instead of collapsing into a mobile toggle. Fix the navbar markup.",
            requirements = listOf(
                "Add .navbar-expand-lg so it collapses on small screens",
                "Add .navbar-toggler button with data-bs-toggle and data-bs-target",
                "Wrap links in .collapse .navbar-collapse with a matching ID",
                "Ensure .navbar-brand is placed before the toggler"
            ),
            starterCode = """<!-- ❌ BROKEN: Missing collapse container and toggler button -->
<nav class="navbar bg-dark text-white">
  <a class="navbar-brand text-white" href="#">MyBrand</a>
  <ul class="nav">
    <li class="nav-item"><a class="nav-link text-white" href="#">Home</a></li>
    <li class="nav-item"><a class="nav-link text-white" href="#">Features</a></li>
    <li class="nav-item"><a class="nav-link text-white" href="#">Pricing</a></li>
  </ul>
</nav>""",
            solutionCode = """<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3">
  <div class="container-fluid">
    <a class="navbar-brand fw-bold text-info" href="#">MyBrand</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link active" href="#">Home</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Features</a></li>
        <li class="nav-item"><a class="nav-link" href="#">Pricing</a></li>
      </ul>
    </div>
  </div>
</nav>""",
            testExpectations = listOf(
                "Includes navbar-expand-lg class",
                "Includes button with class navbar-toggler and navbar-toggler-icon",
                "Wraps links in div with collapse navbar-collapse",
                "IDs match between data-bs-target and collapse div"
            ),
            hint = "Wrap the menu list in <div class=\"collapse navbar-collapse\" id=\"navbarNav\">.",
            solutionExplanation = "Bootstrap requires the .navbar-expand-* class to trigger responsive layout transitions, and the .navbar-toggler button needs data-bs-target pointing to the collapsible div's id."
        )
    )

    // Project Builder catalog
    val projects: List<ProjectItem> = listOf(
        ProjectItem(
            id = "proj_bs_landing",
            title = "Modern SaaS Landing Page",
            category = "Beginner",
            difficulty = "Easy",
            estimatedHours = "2 hours",
            description = "Build a complete landing page with a hero header, responsive feature grid, testimonial cards, pricing table, and footer.",
            requirements = listOf(
                "Responsive navbar with hamburger collapse",
                "Hero section with lead text and two CTA buttons",
                "3-column feature grid using cards with icons",
                "Full-width dark footer with copyright and links"
            ),
            starterCode = """<header class="navbar navbar-expand-lg navbar-dark bg-dark">
  <!-- Navbar markup -->
</header>
<main>
  <!-- Hero Section -->
  <section class="py-5 text-center container">
    <h1 class="display-4 fw-bold">Build Faster with Bootstrap</h1>
    <p class="lead text-muted">Create responsive, mobile-first websites in minutes.</p>
    <button class="btn btn-primary btn-lg">Get Started</button>
  </section>
</main>""",
            milestones = listOf(
                "Responsive Navigation Setup",
                "Hero Section & Call-to-Actions",
                "Features 3-Column Grid",
                "Pricing Comparison & Footer"
            ),
            tags = listOf("Grid", "Navbar", "Hero", "Cards", "Utilities")
        ),
        ProjectItem(
            id = "proj_bs_admin",
            title = "Admin Analytics Dashboard UI",
            category = "Intermediate",
            difficulty = "Medium",
            estimatedHours = "3.5 hours",
            description = "Build an admin dashboard with responsive sidebar offcanvas, stat metric cards with percentage badges, interactive table, and modal.",
            requirements = listOf(
                "Collapsible sidebar navigation (desktop fixed, mobile offcanvas)",
                "Stat cards (Revenue, Active Users, Conversions) with color badges",
                "Responsive striped table with user data and action dropdowns",
                "Confirmation modal dialog for deleting records"
            ),
            starterCode = """<div class="container-fluid">
  <div class="row">
    <!-- Sidebar -->
    <nav class="col-md-3 col-lg-2 d-md-block bg-dark sidebar collapse">
      <!-- Nav items -->
    </nav>
    <!-- Main Content -->
    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
      <h2>Dashboard Overview</h2>
    </main>
  </div>
</div>""",
            milestones = listOf(
                "Sidebar & Layout Architecture",
                "Metric KPI Cards Grid",
                "Data Table with Action Dropdowns",
                "Offcanvas Mobile Drawer"
            ),
            tags = listOf("Dashboard", "Offcanvas", "Tables", "Badges", "Modals")
        )
    )

    // Interview Prep items
    val interviewQuestions: List<InterviewItem> = listOf(
        InterviewItem(
            id = "int_bs_1",
            question = "What does 'mobile-first' mean in Bootstrap, and how is it implemented?",
            category = "Architecture",
            type = "Conceptual",
            detailedAnswer = "Mobile-first means styles are written for the smallest screens (mobile devices) first by default without media queries. As screen sizes increase, min-width media queries (@media (min-width: 576px)) layer on additional styling. For example, .col-12 applies on all screens, while .col-md-6 only activates when the viewport is at least 768px wide.",
            keyTakeaways = listOf(
                "Base styles target mobile screens by default",
                "Bootstrap media queries use min-width rather than max-width",
                "Smaller breakpoint classes inherit upwards unless overridden by larger breakpoints"
            )
        ),
        InterviewItem(
            id = "int_bs_2",
            question = "What is the difference between .container, .container-fluid, and responsive containers?",
            category = "Layout",
            type = "Conceptual",
            detailedAnswer = ".container provides a fixed width that snaps to maximum sizes at each breakpoint (540px, 720px, 960px, 1140px, 1320px) and centers with mx-auto. .container-fluid is always 100% width across all viewports. Responsive containers (.container-md) stay 100% wide until the specified breakpoint (768px), then behave as fixed containers.",
            keyTakeaways = listOf(
                ".container has max-width and centers horizontally",
                ".container-fluid is always 100% wide",
                ".container-{breakpoint} is fluid until the specified breakpoint"
            )
        ),
        InterviewItem(
            id = "int_bs_3",
            question = "Why must columns (.col-*) always be immediate children of a .row in Bootstrap?",
            category = "Grid",
            type = "Conceptual",
            detailedAnswer = "The .row class applies negative horizontal margins (e.g. margin-left: -0.75rem, margin-right: -0.75rem) to counteract the padding (gutters) on its columns. This prevents horizontal scrollbars and ensures column content aligns perfectly with container boundaries. Placing elements between .row and .col breaks this offset math.",
            keyTakeaways = listOf(
                ".row applies negative margins to counteract column padding (gutters)",
                "Direct parent-child relationship preserves alignment math",
                "Containers provide the outer padding that contains the row"
            )
        )
    )

    // Cheat Sheets
    val cheatSheets: List<CheatSheetItem> = listOf(
        CheatSheetItem(
            title = "Bootstrap 5 Breakpoints Reference",
            category = "Breakpoints",
            bullets = listOf(
                "xs: < 576px (default, no infix, e.g. col-12)",
                "sm: ≥ 576px (phones in landscape, e.g. col-sm-6)",
                "md: ≥ 768px (tablets / iPads, e.g. col-md-4)",
                "lg: ≥ 992px (laptops & desktops, e.g. col-lg-3)",
                "xl: ≥ 1200px (large monitors, e.g. col-xl-2)",
                "xxl: ≥ 1400px (ultra-wide monitors, e.g. col-xxl-2)"
            ),
            quickSnippet = "<div class=\"col-12 col-md-6 col-lg-4\">Responsive Column</div>"
        ),
        CheatSheetItem(
            title = "Spacing Utilities Formula",
            category = "Spacing",
            bullets = listOf(
                "m-* for margin, p-* for padding",
                "t (top), b (bottom), s (start/left), e (end/right)",
                "x (horizontal: left+right), y (vertical: top+bottom)",
                "Scale 0 (0), 1 (4px), 2 (8px), 3 (16px), 4 (24px), 5 (48px)",
                "mx-auto centers block elements horizontally"
            ),
            quickSnippet = "<div class=\"p-3 mb-4 mx-auto text-center\">Spaced Box</div>"
        ),
        CheatSheetItem(
            title = "Flexbox Alignment Cheat Sheet",
            category = "Flexbox",
            bullets = listOf(
                "d-flex (enable flexbox container)",
                "flex-row (default) / flex-column (stack vertically)",
                "justify-content-start / center / end / between / around",
                "align-items-start / center / end / stretch",
                "flex-wrap / flex-nowrap"
            ),
            quickSnippet = "<div class=\"d-flex justify-content-between align-items-center\">...</div>"
        )
    )

    // Glossary
    val glossary: List<GlossaryItem> = listOf(
        GlossaryItem(
            term = "Breakpoint",
            category = "Grid",
            definition = "Specific screen widths where responsive layouts adapt to accommodate different device sizes (xs, sm, md, lg, xl, xxl).",
            example = "@media (min-width: 768px) { ... }"
        ),
        GlossaryItem(
            term = "Container",
            category = "Layout",
            definition = "The fundamental layout element in Bootstrap that houses, pads, and aligns content and rows.",
            example = "<div class=\"container\">...</div>"
        ),
        GlossaryItem(
            term = "Gutter",
            category = "Grid",
            definition = "The horizontal and vertical space between columns created by responsive padding (e.g. g-3, gx-2, gy-4).",
            example = "<div class=\"row g-3\">...</div>"
        ),
        GlossaryItem(
            term = "Mobile-First",
            category = "Architecture",
            definition = "A design philosophy where styles for mobile devices are coded first, and min-width media queries scale styles up for larger viewports.",
            example = ".col-12 .col-md-6"
        ),
        GlossaryItem(
            term = "Utility Class",
            category = "Utilities",
            definition = "Single-purpose CSS classes used to apply specific styling rules like spacing, color, display, or alignment directly in HTML.",
            example = "class=\"d-flex p-3 bg-dark text-white rounded\""
        )
    )

    // Achievements
    val achievements: List<AchievementBadge> = listOf(
        AchievementBadge("first_grid", "Grid Architect", "Mastered the 12-column responsive Bootstrap grid.", "GRID", true),
        AchievementBadge("streak_7", "7-Day Streak", "Practiced Bootstrap continuous coding for 7 days.", "STREAK", true),
        AchievementBadge("flex_master", "Flexbox Master", "Built responsive flexbox alignments without custom CSS.", "UTILITY", false),
        AchievementBadge("navbar_pro", "Navbar Specialist", "Constructed a responsive collapsible navbar with toggler.", "COMPONENT", false),
        AchievementBadge("card_builder", "Card Designer", "Designed accessible multi-column card grids.", "COMPONENT", false),
        AchievementBadge("react_bs_bridge", "React-Bootstrap Pioneer", "Integrated Bootstrap components inside React.", "COMPONENT", false),
        AchievementBadge("project_finisher", "Project Finisher", "Built a complete production landing page.", "PROJECT", false),
        AchievementBadge("cert_complete", "Bootstrap Master", "Completed the complete Learn Bootstrap curriculum.", "STREAK", false)
    )

    // Component Lab items
    val componentLabItems: List<UIComponentItem> = listOf(
        UIComponentItem(
            id = "comp_btn",
            name = "Buttons & Button Groups",
            category = "Buttons",
            description = "Pre-styled buttons with semantic colors, outline variants, sizing, and group toggles.",
            htmlSnippet = "<button type=\"button\" class=\"btn btn-primary btn-lg\">Primary Button</button>",
            reactBootstrapSnippet = "<Button variant=\"primary\" size=\"lg\">Primary Button</Button>",
            classNames = listOf("btn", "btn-primary", "btn-outline-secondary", "btn-lg", "btn-sm"),
            accessibilityNotes = "Ensures 48x48dp minimum touch targets, visible focus outlines, and contrast ratios."
        ),
        UIComponentItem(
            id = "comp_card",
            name = "Cards & Surfaces",
            category = "Surfaces",
            description = "Flexible bordered containers with headers, footers, body padding, and shadow effects.",
            htmlSnippet = "<div class=\"card shadow-sm\"><div class=\"card-body\">Content</div></div>",
            reactBootstrapSnippet = "<Card className=\"shadow-sm\"><Card.Body>Content</Card.Body></Card>",
            classNames = listOf("card", "card-header", "card-body", "card-footer", "shadow-sm"),
            accessibilityNotes = "Wrap interactive sections with semantic headings and clear action links."
        ),
        UIComponentItem(
            id = "comp_modal",
            name = "Modals & Dialogs",
            category = "Overlays",
            description = "Interactive dialog windows with backdrops and escape key dismissal.",
            htmlSnippet = "<div class=\"modal fade\"><div class=\"modal-dialog modal-dialog-centered\"><div class=\"modal-content\">...</div></div></div>",
            reactBootstrapSnippet = "<Modal show={show} onHide={handleClose} centered><Modal.Body>Content</Modal.Body></Modal>",
            classNames = listOf("modal", "modal-dialog", "modal-content", "modal-dialog-centered"),
            accessibilityNotes = "Traps focus inside modal while active, locks background scroll."
        ),
        UIComponentItem(
            id = "comp_nav",
            name = "Navbar",
            category = "Navigation",
            description = "Responsive header navigation bar that collapses into a hamburger on small screens.",
            htmlSnippet = "<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\"><a class=\"navbar-brand\">Brand</a></nav>",
            reactBootstrapSnippet = "<Navbar expand=\"lg\" bg=\"dark\" variant=\"dark\"><Navbar.Brand>Brand</Navbar.Brand></Navbar>",
            classNames = listOf("navbar", "navbar-expand-lg", "navbar-brand", "navbar-toggler"),
            accessibilityNotes = "aria-expanded toggled dynamically on hamburger button for screen readers."
        )
    )
}
